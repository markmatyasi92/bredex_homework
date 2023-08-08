package com.jobseeker.exception;

import com.jobseeker.errorhandling.ResponseDto;

public class ApiKeyNotExistException extends HandlerException {

    public ApiKeyNotExistException(ResponseDto response) {
        super(response);
    }
}
