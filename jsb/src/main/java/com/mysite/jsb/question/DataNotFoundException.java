package com.mysite.jsb.question;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public DataNotFoundException(String message) {
        super(message);
    }
}
