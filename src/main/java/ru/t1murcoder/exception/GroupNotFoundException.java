package ru.t1murcoder.exception;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(String message) {
        super(message);
    }
}
