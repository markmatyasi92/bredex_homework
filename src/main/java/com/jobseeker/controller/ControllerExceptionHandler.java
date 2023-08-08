package com.jobseeker.controller;

import com.jobseeker.errorhandling.ErrorDto;
import com.jobseeker.errorhandling.ErrorType;
import com.jobseeker.errorhandling.ResponseDto;
import com.jobseeker.exception.ApiKeyNotExistException;
import com.jobseeker.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllExceptions(Exception exception) {
        var errorMsg = exception.getMessage();
        log.error(errorMsg != null ? errorMsg : "An unknown error occurred.", exception);
        return new ResponseEntity<>(new ResponseDto(new ErrorDto(ErrorType.INTERNAL_SERVER_ERROR, List.of())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception) {
        var response = exception.getResponse();
        log.error(MessageFormatter.arrayFormat(exception.getMessage(), response.getError().getParams().toArray()).getMessage(), exception);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiKeyNotExistException.class)
    public ResponseEntity<ResponseDto> handleApiKeyNotExistException(ApiKeyNotExistException exception) {
        var response = exception.getResponse();
        log.error(MessageFormatter.arrayFormat(exception.getMessage(), response.getError().getParams().toArray()).getMessage(), exception);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception) {
        var additionalErrors = new ArrayList<ErrorDto>();
        for (var violation : exception.getConstraintViolations()) {
            var errorMsg = violation.getMessage();
            var invalidField = violation.getPropertyPath().toString();
            var invalidValue = violation.getInvalidValue();
            log.error(MessageFormatter.arrayFormat(ErrorType.VALIDATION_ERROR.getErrorMessage(), new Object[]{errorMsg, invalidField, invalidValue}).getMessage(), exception);
            additionalErrors.add(new ErrorDto(ErrorType.VALIDATION_ERROR, List.of(errorMsg, invalidField, invalidValue)));
        }

        return new ResponseEntity<>(new ResponseDto(new ErrorDto(ErrorType.INTERNAL_SERVER_ERROR, List.of()), additionalErrors), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        var errorMsg = exception.getMessage();
        log.error(MessageFormatter.arrayFormat(ErrorType.DATA_INTEGRITY_VALIDATION_ERROR.getErrorMessage(),
                                               new Object[]{errorMsg}).getMessage(), exception);
        return new ResponseEntity<>(
                new ResponseDto(new ErrorDto(ErrorType.DATA_INTEGRITY_VALIDATION_ERROR, List.of(errorMsg == null ? "" : errorMsg))),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
