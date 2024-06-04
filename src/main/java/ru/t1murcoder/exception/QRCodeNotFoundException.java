package ru.t1murcoder.exception;

public class QRCodeNotFoundException extends RuntimeException{
    public QRCodeNotFoundException(String message) {
        super(message);
    }
}
