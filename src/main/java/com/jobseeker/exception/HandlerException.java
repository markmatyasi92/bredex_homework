package com.jobseeker.exception;

import com.jobseeker.errorhandling.ResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class HandlerException extends RuntimeException {

    private ResponseDto response;

    public HandlerException(ResponseDto response) {
        super(response.getError().getErrorCode().getErrorMessage());
        this.response = response;
    }
}
