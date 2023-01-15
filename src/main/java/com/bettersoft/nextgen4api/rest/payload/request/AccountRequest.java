package com.bettersoft.nextgen4api.rest.payload.request;

import com.bettersoft.nextgen4api.model.Account;
import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.rest.payload.request.generic.GenericRequest;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;

@Data
public class AccountRequest implements GenericRequest<AccountRequest, Account> {

    private String uuid;

    @NotNull
    private String name;
    private String description;

    @NotNull
    private Status status;


    @Override
    public Account toEntity(final ModelMapper modelMapper, final AccountRequest request) {
        return modelMapper.map(request, Account.class);
    }

    @Override
    public AccountRequest toRequest(final ModelMapper modelMapper, final Account entity) {
        return modelMapper.map(entity, AccountRequest.class);
    }

    @Override
    public Account merge(final ModelMapper modelMapper, final Account entity, final AccountRequest request) {
        skipMapIds(modelMapper, AccountRequest.class, Account.class);
        mergeRequest(modelMapper, request, entity);
        return  entity;
    }

}
