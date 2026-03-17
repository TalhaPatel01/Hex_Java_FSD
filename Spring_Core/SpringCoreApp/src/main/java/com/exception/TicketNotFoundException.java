package com.exception;

public class TicketNotFoundException extends RuntimeException{
    private String message;

    public TicketNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
