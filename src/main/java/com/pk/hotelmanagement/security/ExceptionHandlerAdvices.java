package com.pk.hotelmanagement.security;

import com.pk.hotelmanagement.errors.ErrorMessage;
import com.pk.hotelmanagement.hotel.NotFoundException;
import com.pk.hotelmanagement.users.login.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvices extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<?> handleIllegalArgumenException(IllegalArgumentException exception, WebRequest request) {
        ErrorMessage message = new ErrorMessage(400, exception.getMessage());
        return handleExceptionInternal(exception, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<?> handleUserNotFoundException(UserNotFoundException exception, WebRequest request) {
        ErrorMessage message = new ErrorMessage(404, exception.getMessage());
        return handleExceptionInternal(exception, message, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<?> handleNotFoundException(NotFoundException exception, WebRequest request) {
        ErrorMessage message = new ErrorMessage(404, exception.getMessage());
        return handleExceptionInternal(exception, message, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
