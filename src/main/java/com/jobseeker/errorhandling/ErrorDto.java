package com.jobseeker.errorhandling;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ErrorDto {
    private ErrorType errorCode;
    private List<Object> params;

    public ErrorDto(ErrorType errorCode, List<Object> params) {
        this.errorCode = errorCode;
        this.params = params;
    }
}
