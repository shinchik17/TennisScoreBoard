package com.alexshin.tennisscoreboard.exception;

public class IllegalUuidFormatException extends IllegalArgumentException{
    public IllegalUuidFormatException(String s) {
        super("Illegal uuid value: " + s);
    }
}
