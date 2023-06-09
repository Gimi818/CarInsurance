package com.carinsurance.car.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class CarControllerErrorHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CarNotFoundException.class)
    @ResponseBody
    public CarErrorResponse carNotFound(CarNotFoundException exception) {
        final String message = exception.getMessage();
        log.error(message);
        return new
                CarErrorResponse(message, HttpStatus.NOT_FOUND);
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CarHasAPolicyException.class)
    @ResponseBody
    public CarErrorResponse carHasAPolicy(CarHasAPolicyException exception) {
        final String message = exception.getMessage();
        log.error(message);
        return new
                CarErrorResponse(message, HttpStatus.CONFLICT);
    }



}
