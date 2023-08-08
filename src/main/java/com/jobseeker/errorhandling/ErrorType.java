package com.jobseeker.errorhandling;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    INTERNAL_SERVER_ERROR("An unknown error occurred."),
    VALIDATION_ERROR("Validation error occurred. message: {}. field: {} value: {}"),
    DATA_INTEGRITY_VALIDATION_ERROR("Data integrity validation error occurred. message: {}"),
    API_KEY_NOT_EXIST_ERROR("Api key does not exist. value: {}"),
    POSITION_NOT_FOUND_ERROR("Position not found with id: {}");

    private final String errorMessage;
}
