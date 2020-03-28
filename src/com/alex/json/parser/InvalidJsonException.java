package com.alex.json.parser;

public class InvalidJsonException extends Exception {
    public InvalidJsonException() {
        super();
    }
    
    public InvalidJsonException(String message) {
        super(message);
    }
}