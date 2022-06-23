package sk.stuba.fei.uim.oop.assignment3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundException() {}

    @ExceptionHandler(IllegalOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleIllegalOperationException() {}

}