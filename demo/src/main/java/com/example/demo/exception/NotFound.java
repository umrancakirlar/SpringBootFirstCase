package com.example.demo.exception;

public class NotFound extends RuntimeException{
    public static final Long serialVersionUID=1L;
    public NotFound(String message) {
        super(message);
    }
}