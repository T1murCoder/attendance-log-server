package ru.t1murcoder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1murcoder.domain.Lesson;
import ru.t1murcoder.repository.GroupRepository;
import ru.t1murcoder.repository.LessonRepository;
import ru.t1murcoder.service.LessonService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    // TODO: СДелать сервис управления урока
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;

    @Override
    public Lesson add(Lesson lesson) {

        if (lesson.getTheme() != null)
            throw new RuntimeException("Lesson must have theme");
        if (lesson.getTimeStart() != null)
            throw new RuntimeException("Lesson must have start time");
        if (lesson.getTimeEnd() != null)
            throw new RuntimeException("Lesson must have end time");
        if (lesson.getDate() != null)
            throw new RuntimeException("Lesson must have date");
        if (lesson.getGroup() != null)
            throw new RuntimeException("Lesson must have group");
        if (groupRepository.findById(lesson.getGroup().getId()).isEmpty())
            throw new RuntimeException("Group does not exist");

        return lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson getById(long id) {

        Optional<Lesson> lessonOptional = lessonRepository.findById(id);

        if (lessonOptional.isEmpty()) throw new RuntimeException("Lesson not found");

        return lessonOptional.get();
    }

    @Override
    public Lesson update(Lesson lesson) {
        return null;
    }

    @Override
    public void deleteById(long id) {
        lessonRepository.deleteById(id);
    }
}
