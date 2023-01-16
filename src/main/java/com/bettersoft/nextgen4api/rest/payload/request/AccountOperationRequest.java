package com.bettersoft.nextgen4api.rest.payload.request;

import com.bettersoft.nextgen4api.model.AccountOperation;
import com.bettersoft.nextgen4api.model.enums.OperationType;
import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.rest.payload.request.generic.GenericRequest;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;

@Data
public class AccountOperationRequest implements GenericRequest<AccountOperationRequest, AccountOperation> {

    private String uuid;

    @NotNull
    private String name;

    @NotNull
    private OperationType operationType;

    private String description;

    @NotNull
    private Status status;


    @Override
    public AccountOperation toEntity(final ModelMapper modelMapper, final AccountOperationRequest request) {
        return modelMapper.map(request, AccountOperation.class);
    }

    @Override
    public AccountOperationRequest toRequest(final ModelMapper modelMapper, final AccountOperation entity) {
        return modelMapper.map(entity, AccountOperationRequest.class);
    }

    @Override
    public AccountOperation merge(final ModelMapper modelMapper, final AccountOperation entity, final AccountOperationRequest request) {
        skipMapIds(modelMapper, AccountOperationRequest.class, AccountOperation.class);
        mergeRequest(modelMapper, request, entity);
        return  entity;
    }

}
