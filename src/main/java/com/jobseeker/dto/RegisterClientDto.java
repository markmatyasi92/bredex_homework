package com.jobseeker.dto;

import com.jobseeker.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterClientDto {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @ValidEmail
    private String email;
}
