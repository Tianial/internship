package com.parcaune.demo.exceptions;

public class StudentAppBadRequestException extends StudentAppRunTimeException {
    public StudentAppBadRequestException() {
        super();
    }

    public StudentAppBadRequestException(String message) {

        super(message);
    }

    public StudentAppBadRequestException(Throwable cause) {
        super(cause);
    }

    public StudentAppBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
