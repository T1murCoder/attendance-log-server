package ru.t1murcoder.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.t1murcoder.domain.Authority;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig{

    private final UserDetailsService userDetailsService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(withDefaults())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/teacher/register")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/teacher/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/teacher/username/{username}")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/teacher/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/student/register")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/student/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/student/username/{username}")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/teacher/**")).hasAuthority("ROLE_TEACHER")
                        .requestMatchers(new AntPathRequestMatcher("/student/**")).hasAnyAuthority("ROLE_STUDENT", "ROLE_TEACHER")
                        .requestMatchers(new AntPathRequestMatcher("/group/**")).hasAnyAuthority("ROLE_STUDENT", "ROLE_TEACHER")
                        .requestMatchers(new AntPathRequestMatcher("/lesson/**")).hasAnyAuthority("ROLE_STUDENT", "ROLE_TEACHER")
                        .requestMatchers(new AntPathRequestMatcher("/attendance/**")).hasAnyAuthority("ROLE_STUDENT", "ROLE_TEACHER")
                        .requestMatchers(new AntPathRequestMatcher("/qrcode/**")).hasAnyAuthority("ROLE_STUDENT", "ROLE_TEACHER")
//                        .requestMatchers(new AntPathRequestMatcher("/student/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/student/**")).hasAuthority("ROLE_STUDENT")
//                        .requestMatchers(new AntPathRequestMatcher("/group/{id}")).hasAnyAuthority("ROLE_STUDENT", "ROLE_TEACHER") // ЗДЕСЬ ВРЕМЕННО ОТКЛЮЧЕНО
//                        .requestMatchers(new AntPathRequestMatcher("/group/student/{id}")).hasAnyAuthority("ROLE_STUDENT", "ROLE_TEACHER")
//                        .requestMatchers(new AntPathRequestMatcher("/group/**")).hasAuthority("ROLE_TEACHER")
                        .requestMatchers(HttpMethod.GET, "/hello_auth").hasAuthority("ROLE_TEACHER")
                        .requestMatchers(HttpMethod.GET, "/hello").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
