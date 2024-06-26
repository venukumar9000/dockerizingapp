package com.example.UserService.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExpectionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFound resourceNotFound, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                resourceNotFound.getMessage(),
                webRequest.getDescription(false),
                "User not found"


        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmailAlreadyExitsException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(EmailAlreadyExitsException resourceNotFound, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                resourceNotFound.getMessage(),
                webRequest.getDescription(false),
                "EMAIL IS ALREADY PRESENT IN DB"


        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> exception(Exception exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "Internal server error"


        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INSUFFICIENT_STORAGE);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String,String > errors = new HashMap<>();
      List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
       errorList.forEach((error)->{
               String fieldName = ((FieldError) error).getField();
               String message = error.getDefaultMessage();
               errors.put(fieldName,message);

       })
       ;
return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
