package com.bettersoft.nextgen4api.service;

import com.bettersoft.nextgen4api.repository.ContractModelRepository;
import com.bettersoft.nextgen4api.rest.payload.request.ContractModelRequest;
import com.bettersoft.nextgen4api.service.generic.CrudService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContractModelService implements CrudService<ContractModelRequest> {

    private final ContractModelRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public void validate(final boolean toPersist, final ContractModelRequest request) {

    }

}
