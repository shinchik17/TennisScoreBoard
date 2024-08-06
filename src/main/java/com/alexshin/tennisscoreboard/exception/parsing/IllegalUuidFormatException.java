package com.alexshin.tennisscoreboard.exception.parsing;

public class IllegalUuidFormatException extends IllegalArgumentException{
    public IllegalUuidFormatException(String s) {
        super("Illegal uuid value: " + s);
    }
}
