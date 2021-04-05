package com.parcaune.demo.exceptions;

public class StudentAppRunTimeException extends RuntimeException {
    public StudentAppRunTimeException() {
    }

    public StudentAppRunTimeException(String message) {
        super(message);
    } // super call the mothers constructor

    public StudentAppRunTimeException(Throwable cause) {
        super(cause);
    }

    public StudentAppRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
