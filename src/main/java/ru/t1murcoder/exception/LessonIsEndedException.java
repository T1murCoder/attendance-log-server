package ru.t1murcoder.exception;

public class LessonIsEndedException extends RuntimeException {
    public LessonIsEndedException(String message) {
        super(message);
    }
}
