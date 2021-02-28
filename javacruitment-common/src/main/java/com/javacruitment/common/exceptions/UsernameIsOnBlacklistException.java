package com.javacruitment.common.exceptions;

public class UsernameIsOnBlacklistException extends Exception {
    public UsernameIsOnBlacklistException(String message) {
        super(message);
    }
}
