package com.project.cinema.mapper;

import com.project.cinema.dto.request.HallDtoRequest;
import com.project.cinema.model.Hall;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface HallMapper {
    HallMapper INSTANCE= Mappers.getMapper(HallMapper.class);

    Hall hallDtoRequestToHall(HallDtoRequest hallDtoRequest);
}
