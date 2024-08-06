package com.alexshin.tennisscoreboard.exception.service;

import java.util.NoSuchElementException;

public class NoSuchMatchException extends NoSuchElementException {

    public NoSuchMatchException(String s) {
        super(s);
    }
}
