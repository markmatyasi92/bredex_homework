package com.jobseeker.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class ResponseDto {
    private ErrorDto error;
    private List<ErrorDto> additionalErrors;
    private List<ErrorDto> additionalWarnings;

    public ResponseDto(ErrorDto error) {
        this.error = error;
        this.additionalErrors = new ArrayList<>();
    }

    public ResponseDto(ErrorDto error, List<ErrorDto> additionalErrors) {
        this.error = error;
        this.additionalErrors = additionalErrors;
    }
}
