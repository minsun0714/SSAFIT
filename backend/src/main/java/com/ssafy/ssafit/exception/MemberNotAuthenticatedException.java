package com.ssafy.ssafit.exception;

public class MemberNotAuthenticatedException extends RuntimeException {
    public MemberNotAuthenticatedException(String message) {
        super(message);
    }
}
