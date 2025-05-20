package com.goormthonuniv.hoseo.hosiktamtam.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}

public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}

public class UnauthorizedException extends BaseException {

    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}

public class ForbiddenException extends BaseException {

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}

public class ConflictException extends BaseException {

    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}

public class InternalServerErrorException extends BaseException {

    public InternalServerErrorException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
