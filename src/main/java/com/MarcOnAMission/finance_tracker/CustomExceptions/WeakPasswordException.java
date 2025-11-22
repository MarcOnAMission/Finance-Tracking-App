package com.MarcOnAMission.finance_tracker.CustomExceptions;

public class WeakPasswordException extends RuntimeException{
    public WeakPasswordException(String message){
        super(message);
    }
}
