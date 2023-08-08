package com.jobseeker.controller;

import com.jobseeker.dto.CreatePositionDto;
import com.jobseeker.dto.PositionOutputDto;
import com.jobseeker.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/position")
public class PositionController {

    private final PositionService positionService;

    @PostMapping
    public String createPosition(@RequestBody @Valid CreatePositionDto createPositionDto, Errors errors,
                                 @RequestHeader("Api-Key") UUID apiKey) {
        return positionService.createPosition(createPositionDto, apiKey);
    }

    @GetMapping("/search")
    public List<String> getPositions(@RequestParam("keyword") @NotBlank @Size(max = 50) String keyword,
                                     @RequestParam("location") @NotBlank @Size(max = 50) String location,
                                     @RequestHeader("Api-Key") UUID apiKey) {
        return positionService.getPositions(keyword, location, apiKey);
    }

    @GetMapping("/{id}")
    public PositionOutputDto getPosition(@PathVariable("id") Long id,
                                         @RequestHeader("Api-Key") UUID apiKey) {
        return positionService.getPosition(id, apiKey);
    }
}
