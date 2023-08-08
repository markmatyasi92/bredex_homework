package com.jobseeker.service;

import com.jobseeker.dto.CreatePositionDto;
import com.jobseeker.dto.PositionOutputDto;
import com.jobseeker.errorhandling.ErrorDto;
import com.jobseeker.errorhandling.ErrorType;
import com.jobseeker.errorhandling.ResponseDto;
import com.jobseeker.exception.ResourceNotFoundException;
import com.jobseeker.mapper.PositionMapper;
import com.jobseeker.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PositionService {

    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;
    private final ClientService clientService;

    public String createPosition(CreatePositionDto createPositionDto, UUID apiKey) {
        clientService.checkApiKey(apiKey);

        var position = positionMapper.toEntity(createPositionDto);
        position.setUrl("https://www.example.com/" + position.getName().replaceAll(" ", "-"));

        return positionRepository.save(position).getUrl();
    }

    public List<String> getPositions(String keyword, String location, UUID apiKey) {
        clientService.checkApiKey(apiKey);

        return positionRepository.findPositions(keyword, location);
    }

    public PositionOutputDto getPosition(Long id, UUID apiKey) {
        clientService.checkApiKey(apiKey);

        var position = positionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(new ResponseDto(new ErrorDto(ErrorType.POSITION_NOT_FOUND_ERROR, List.of(id)))));

        return positionMapper.toDto(position);
    }
}
