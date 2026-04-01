package com.springboot.myapp.exception;

public class TicketUpdatePermissionException extends RuntimeException{
    public TicketUpdatePermissionException(String message){
        super(message);
    }
}