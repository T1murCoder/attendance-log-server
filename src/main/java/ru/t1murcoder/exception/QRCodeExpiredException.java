package ru.t1murcoder.exception;

public class QRCodeExpiredException extends RuntimeException{
    public QRCodeExpiredException(String message) {
        super(message);
    }
}
