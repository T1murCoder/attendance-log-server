package ru.t1murcoder.mapper;

import lombok.experimental.UtilityClass;
import ru.t1murcoder.controller.dto.LessonDto;
import ru.t1murcoder.domain.Lesson;

@UtilityClass
public class LessonMapper {

    public LessonDto toLessonDto(Lesson lesson) {

        LessonDto lessonDto = LessonDto.builder()
                .id(lesson.getId())
                .theme(lesson.getTheme())
                .timeStart(lesson.getTimeStart())
                .timeEnd(lesson.getTimeEnd())
                .date(lesson.getDate())
                .build();

        if (lesson.getGroup() != null) {
            lessonDto.setGroupId(lesson.getGroup().getId());
            lessonDto.setGroupName(lesson.getGroup().getName());
        }

        return lessonDto;
    }

    public Lesson toLessonEntity(LessonDto lessonDto) {

        Lesson lesson = Lesson.builder()
                .theme(lessonDto.getTheme())
                .timeStart(lessonDto.getTimeStart())
                .timeEnd(lessonDto.getTimeEnd())
                .date(lessonDto.getDate())
                .build();

        if (lessonDto.getId() != null)
            lesson.setId(lessonDto.getId());

        return lesson;
    }
}
