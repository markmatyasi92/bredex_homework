package com.jobseeker.service;

import com.jobseeker.errorhandling.ErrorDto;
import com.jobseeker.errorhandling.ErrorType;
import com.jobseeker.errorhandling.ResponseDto;
import com.jobseeker.exception.ApiKeyNotExistException;
import com.jobseeker.mapper.ClientMapper;
import com.jobseeker.dto.RegisterClientDto;
import com.jobseeker.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    public UUID registerClient(RegisterClientDto registerClientDto) {
        var client = clientMapper.toEntity(registerClientDto);

        var apiKeyForClient = UUID.randomUUID();
        client.setApiKey(apiKeyForClient);
        clientRepository.save(client);

        return apiKeyForClient;
    }

    public void checkApiKey(UUID apiKey) {
        if (!clientRepository.existsByApiKey(apiKey)) {
            throw new ApiKeyNotExistException(
                    new ResponseDto(new ErrorDto(ErrorType.API_KEY_NOT_EXIST_ERROR, List.of(apiKey))));
        }
    }

}
