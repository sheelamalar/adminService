package com.reservation.adminService.exception;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReservationExceptionHandler {
	Logger logger = LoggerFactory.getLogger(ReservationExceptionHandler.class);
	
	@ExceptionHandler(ReservationException.class)
    protected ResponseEntity handleGlobalException(ReservationException exception, Locale locale) {
		logger.error(exception.getMessage());
        return new ResponseEntity(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleException(Exception e, Locale locale) {
        return ResponseEntity
                .badRequest()
                .body("Exception occur inside API " + e);
    }
}
