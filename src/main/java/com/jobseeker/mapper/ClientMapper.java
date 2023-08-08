package com.jobseeker.mapper;

import com.jobseeker.domain.Client;
import com.jobseeker.dto.RegisterClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    Client toEntity(RegisterClientDto registerClientDto);
}
