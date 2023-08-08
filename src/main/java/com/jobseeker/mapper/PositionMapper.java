package com.jobseeker.mapper;

import com.jobseeker.domain.Position;
import com.jobseeker.dto.CreatePositionDto;
import com.jobseeker.dto.PositionOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PositionMapper {

    Position toEntity(CreatePositionDto createPositionDto);

    PositionOutputDto toDto(Position position);

}
