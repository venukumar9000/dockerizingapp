package com.example.UserService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExitsException extends RuntimeException{

    private String message;
    public EmailAlreadyExitsException(String message){
        super(message);
    }
}
