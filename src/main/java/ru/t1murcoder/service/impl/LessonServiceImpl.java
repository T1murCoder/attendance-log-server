package ru.t1murcoder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1murcoder.controller.dto.LessonDto;
import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Lesson;
import ru.t1murcoder.exception.GroupNotFoundException;
import ru.t1murcoder.exception.LessonNotFoundException;
import ru.t1murcoder.mapper.GroupMapper;
import ru.t1murcoder.mapper.LessonMapper;
import ru.t1murcoder.repository.GroupRepository;
import ru.t1murcoder.repository.LessonRepository;
import ru.t1murcoder.service.LessonService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final GroupRepository groupRepository;
    private final LessonRepository lessonRepository;

    @Override
    public LessonDto add(LessonDto lessonDto) {

        Optional<Group> group = groupRepository.findById(lessonDto.getGroupId());

        if (group.isEmpty())
            throw new GroupNotFoundException("Group with ID " + lessonDto.getGroupId() + " not found");
        if (lessonDto.getTheme() == null)
            throw new RuntimeException("Lesson must have theme");
        if (lessonDto.getTimeStart() == null)
            throw new RuntimeException("Lesson must have start time");
        if (lessonDto.getTimeEnd() == null)
            throw new RuntimeException("Lesson must have end time");
        if (lessonDto.getDate() == null)
            throw new RuntimeException("Lesson must have date");

        Lesson lesson = LessonMapper.toLessonEntity(lessonDto);

        lesson.setGroup(group.get());

        return LessonMapper.toLessonDto(lessonRepository.save(lesson));
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
    public LessonDto update(LessonDto lessonDto) {
        // TODO: сделать обновление уроков
        return null;
    }

    @Override
    public void deleteById(long id) {
        // TODO: сдлелать удаление уроков и присутствий
        Lesson lesson = lessonRepository.findById(id)
                        .orElseThrow(
                                () -> new LessonNotFoundException("Lesson with ID " + id + " not found")
                        );


        lessonRepository.deleteById(id);
    }
}
