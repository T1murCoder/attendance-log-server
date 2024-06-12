package ru.t1murcoder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1murcoder.controller.dto.LessonDto;
import ru.t1murcoder.controller.dto.QRCodeDto;
import ru.t1murcoder.domain.*;
import ru.t1murcoder.exception.*;
import ru.t1murcoder.mapper.GroupMapper;
import ru.t1murcoder.mapper.LessonMapper;
import ru.t1murcoder.repository.*;
import ru.t1murcoder.service.LessonService;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final GroupRepository groupRepository;
    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final QRCodeRepository qrCodeRepository;

    @Override
    public LessonDto add(LessonDto lessonDto) {
        Group group = groupRepository.findById(lessonDto.getGroupId())
                .orElseThrow(
                        () -> new GroupNotFoundException("Group with ID " + lessonDto.getGroupId() + " not found")
                );

        if (lessonDto.getTheme() == null)
            throw new RuntimeException("Lesson must have theme");
        if (lessonDto.getTimeStart() == null)
            throw new RuntimeException("Lesson must have start time");
        if (lessonDto.getTimeEnd() == null)
            throw new RuntimeException("Lesson must have end time");
        if (lessonDto.getDate() == null)
            throw new RuntimeException("Lesson must have date");

        Lesson lesson = LessonMapper.toLessonEntity(lessonDto);

        lesson.setGroup(group);

        Lesson save = lessonRepository.save(lesson);

        for (Student student : group.getStudentList()) {

            Attendance attendance = Attendance.builder()
                    .isVisited(false)
                    .points(0)
                    .build();

            attendance.setStudent(student);
            attendance.setLesson(save);

            attendanceRepository.save(attendance);
        }

        return LessonMapper.toLessonDto(save);
    }

    @Override
    public List<LessonDto> getAll() {
        return lessonRepository.findAll()
                .stream()
                .map(LessonMapper::toLessonDto)
                .collect(Collectors.toList());
    }

    @Override
    public LessonDto getById(long id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);

        if (lesson.isEmpty())
            throw new LessonNotFoundException("Lesson with ID " + id + " not found");

        return LessonMapper.toLessonDto(lesson.get());
    }

    @Override
    public List<LessonDto> getByTeacherUsername(String username) {
        Teacher teacher = teacherRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException("Teacher with name " + username + " not found")
                );

        List<Lesson> lessonList = lessonRepository.findByGroupTeacherUsername(username);

        return lessonList.stream()
                .map(LessonMapper::toLessonDto)
                .collect(Collectors.toList());
    }

    @Override
    public LessonDto markAttendedByLessonId(Long lessonId, Long studentId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(
                        () -> new LessonNotFoundException("Lesson with ID " + lessonId + " not found")
                );

        Student student = studentRepository.findById(studentId)
                .orElseThrow(
                        () -> new UserNotFoundException("Student with ID " + studentId + " not found")
                );

        Optional<Attendance> attendanceOptional = attendanceRepository.findByLessonIdAndStudentId(lessonId, studentId);

        Attendance attendance;

        if (attendanceOptional.isEmpty()) {

            attendance = Attendance.builder()
                    .lesson(lesson)
                    .student(student)
                    .isVisited(true)
                    .build();
        } else {
            attendance = attendanceOptional.get();
            attendance.setIsVisited(true);
        }

        attendanceRepository.save(attendance);

        return LessonMapper.toLessonDto(lesson);
    }

    @Override
    public LessonDto markAttendedByQRCodeId(Long qrCodeId, Long studentId) {

        QRCode qrCode = qrCodeRepository.findById(qrCodeId)
                .orElseThrow(
                        () -> new QRCodeNotFoundException("QRCode with ID " + qrCodeId + " not found")
                );

        // TODO: Проверить метод
        GregorianCalendar currentTime = new GregorianCalendar();

        if (!(currentTime.after(qrCode.getCreatedAt()) && currentTime.before(qrCode.getExpiresAt()))) {
            throw new QRCodeExpiredException("QRCode with ID " + qrCodeId + " expired");
        }

        Long lessonId = qrCode.getLesson().getId();

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(
                        () -> new LessonNotFoundException("Lesson with ID " + lessonId + " not found")
                );

        Student student = studentRepository.findById(studentId)
                .orElseThrow(
                        () -> new UserNotFoundException("Student with ID " + studentId + " not found")
                );

        Optional<Attendance> attendanceOptional = attendanceRepository.findByLessonIdAndStudentId(lessonId, studentId);

        Attendance attendance;

        if (attendanceOptional.isEmpty()) {

            attendance = Attendance.builder()
                    .lesson(lesson)
                    .student(student)
                    .isVisited(true)
                    .build();
        } else {
            attendance = attendanceOptional.get();
            attendance.setIsVisited(true);
        }

        attendanceRepository.save(attendance);

        return LessonMapper.toLessonDto(lesson);
    }

    @Override
    public void deleteById(long id) {
        Lesson lesson = lessonRepository.findById(id)
                        .orElseThrow(
                                () -> new LessonNotFoundException("Lesson with ID " + id + " not found")
                        );


        lessonRepository.deleteById(id);
    }
}
