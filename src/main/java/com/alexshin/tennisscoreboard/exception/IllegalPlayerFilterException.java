package com.alexshin.tennisscoreboard.exception;

public class IllegalPlayerFilterException extends IllegalArgumentException{
    public IllegalPlayerFilterException(String s) {
        super("Illegal player name: %s. Name should contain only letters and spaces".formatted(s));
    }
}
