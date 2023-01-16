package com.bettersoft.nextgen4api.rest.payload.request;

import com.bettersoft.nextgen4api.model.ContractModel;
import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.rest.payload.request.generic.GenericRequest;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;

@Data
public class ContractModelRequest implements GenericRequest<ContractModelRequest, ContractModel> {

    private String uuid;

    @NotNull
    private Long price;

    @NotNull
    private Integer qtdQuotes;

    @NotNull
    private Long quotePrice;

    private String description;

    @NotNull
    private Status status;


    @Override
    public ContractModel toEntity(final ModelMapper modelMapper, final ContractModelRequest request) {
        return modelMapper.map(request, ContractModel.class);
    }

    @Override
    public ContractModelRequest toRequest(final ModelMapper modelMapper, final ContractModel entity) {
        return modelMapper.map(entity, ContractModelRequest.class);
    }

    @Override
    public ContractModel merge(final ModelMapper modelMapper, final ContractModel entity, final ContractModelRequest request) {
        skipMapIds(modelMapper, ContractModelRequest.class, ContractModel.class);
        mergeRequest(modelMapper, request, entity);
        return  entity;
    }

}
