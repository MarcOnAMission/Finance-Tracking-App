package com.MarcOnAMission.finance_tracker.CustomExceptions;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message) {
        super(message);
    }
}
