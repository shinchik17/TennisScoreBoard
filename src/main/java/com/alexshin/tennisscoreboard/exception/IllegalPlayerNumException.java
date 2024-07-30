package com.alexshin.tennisscoreboard.exception;

public class IllegalPlayerNumException extends IllegalArgumentException{
    public IllegalPlayerNumException(String s) {
        super("Illegal player number: %s. Must be 1 or 2".formatted(s));
    }
}
