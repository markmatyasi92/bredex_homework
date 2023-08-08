package com.jobseeker.exception;

import com.jobseeker.errorhandling.ResponseDto;

public class ResourceNotFoundException extends HandlerException {

    public ResourceNotFoundException(ResponseDto response) {
        super(response);
    }

}
