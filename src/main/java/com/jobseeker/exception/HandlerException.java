package com.jobseeker.exception;

import com.jobseeker.errorhandling.ResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class HandlerException extends RuntimeException {

    private ResponseDto response;

    public HandlerException(String message, ResponseDto response) {
        super(message);
        this.response = response;
    }

    public HandlerException(Throwable cause, ResponseDto response) {
        super(response.getError().getErrorCode().getErrorMessage(), cause);
        this.response = response;
    }

    public HandlerException(String message, Throwable cause, ResponseDto response) {
        super(message, cause);
        this.response = response;
    }

    public HandlerException(ResponseDto response) {
        super(response.getError().getErrorCode().getErrorMessage());
        this.response = response;
    }
}
