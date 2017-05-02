package com.aessense.agm.sensorhistory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.aessense.agm.sensorhistory.exception.BadRequestException;
import com.aessense.agm.sensorhistory.exception.ForbiddenException;
import com.aessense.agm.sensorhistory.exception.ServerException;
import com.aessense.agm.sensorhistory.exception.UnauthorizedException;
import com.aessense.agm.sensorhistory.rest.ErrorResponse;

/**
 * Controller for handling errors.  Allows for custom error response.
 * 
 * @author John Long
 *
 */
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity handleUnauthorized(final Throwable throwable, final Model model) {
    	
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(new ErrorResponse((ServerException) throwable));
    }
    
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity handleForbidden(final Throwable throwable, final Model model) {
    	return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(new ErrorResponse((ServerException) throwable));
    }
    
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleBadRequest(final Throwable throwable, final Model model) {
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(new ErrorResponse((ServerException) throwable));
    }
    
    /**
     * Handle missing required request parameters.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        ErrorResponse response = new ErrorResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setFields(name);
        response.setMessage("Missing required field");
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(response);
        
    }
}
