package com.digdes.school;

public class InputFormatException extends RuntimeException {
    public InputFormatException(String msg) {
        super("Wrong input format: " + msg);
    }
}
