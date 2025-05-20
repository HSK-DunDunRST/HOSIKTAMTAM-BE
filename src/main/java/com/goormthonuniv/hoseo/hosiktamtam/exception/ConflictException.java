package com.goormthonuniv.hoseo.hosiktamtam.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends BaseException {
    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}