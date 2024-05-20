package ru.t1murcoder.exception;

public class LessonNotFoundException extends RuntimeException{
    public LessonNotFoundException(String message) {
        super(message);
    }
}
