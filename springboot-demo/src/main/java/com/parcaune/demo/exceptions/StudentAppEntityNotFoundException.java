package com.parcaune.demo.exceptions;

public class StudentAppEntityNotFoundException extends StudentAppRunTimeException {
    public StudentAppEntityNotFoundException() {
        super();
    }

    public StudentAppEntityNotFoundException(String message) {

        super(message);
    }

    public StudentAppEntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public StudentAppEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
