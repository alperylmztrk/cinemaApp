package com.project.cinema.mapper;

import com.project.cinema.dto.request.HallDtoRequest;
import com.project.cinema.dto.request.SessionDtoRequest;
import com.project.cinema.model.Hall;
import com.project.cinema.model.Session;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        builder = @Builder(disableBuilder = true))
public interface SessionMapper {
    SessionMapper INSTANCE= Mappers.getMapper(SessionMapper.class);

    Session sessionDtoRequestToSession(SessionDtoRequest sessionDtoRequest);
}
