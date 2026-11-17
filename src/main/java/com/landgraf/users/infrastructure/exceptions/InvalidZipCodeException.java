package com.landgraf.users.infrastructure.exceptions;

public class InvalidZipCodeException extends RuntimeException {
    public InvalidZipCodeException(String message) {
        super(message);
    }

    public InvalidZipCodeException(String message, Throwable throwable){ super(message, throwable); }
}
