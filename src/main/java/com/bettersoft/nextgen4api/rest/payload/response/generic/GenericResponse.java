package com.bettersoft.nextgen4api.rest.payload.response.generic;

import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.rest.payload.response.UserResponse;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class GenericResponse<E, R> {

    private Long id;
    private String uuid;
    protected Status status;
    protected LocalDateTime createdAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    protected LocalDateTime updatedAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    protected LocalDateTime deletedAt;

    protected UserResponse createdBy;
    protected UserResponse updatedBy;
    protected UserResponse deletedBy;

    public abstract R toResponse(final ModelMapper modelMapper, final E entity);
}
