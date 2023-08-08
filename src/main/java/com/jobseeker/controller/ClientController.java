package com.jobseeker.controller;

import com.jobseeker.dto.RegisterClientDto;
import com.jobseeker.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public UUID registerClient(@RequestBody @Valid RegisterClientDto registerClientDto, Errors errors) {
        return clientService.registerClient(registerClientDto);
    }
}
