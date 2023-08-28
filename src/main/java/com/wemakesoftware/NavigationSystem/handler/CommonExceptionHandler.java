package com.wemakesoftware.NavigationSystem.handler;

import com.wemakesoftware.NavigationSystem.exception.MobileStationDetectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = {MobileStationDetectionException.class})
    public ResponseEntity<String> handleMobileStationDetectionException(MobileStationDetectionException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
