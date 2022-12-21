package com.bettersoft.nextgen4api.rest.payload.request;

import com.bettersoft.nextgen4api.model.Rota;
import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.rest.payload.request.generic.GenericRequest;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;

@Data
public class RotaRequest implements GenericRequest<RotaRequest, Rota> {

    private String uuid;

    @NotNull
    private String name;
    private String description;

    @NotNull
    private Status status;


    @Override
    public Rota toEntity(final ModelMapper modelMapper, final RotaRequest request) {
        return modelMapper.map(request, Rota.class);
    }

    @Override
    public RotaRequest toRequest(final ModelMapper modelMapper, final Rota entity) {
        return modelMapper.map(entity, RotaRequest.class);
    }

    @Override
    public Rota merge(final ModelMapper modelMapper, final Rota entity, final RotaRequest request) {
        skipMapIds(modelMapper, RotaRequest.class, Rota.class);
        mergeRequest(modelMapper, request, entity);
        return  entity;
    }

}
