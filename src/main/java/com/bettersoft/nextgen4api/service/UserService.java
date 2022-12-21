package com.bettersoft.nextgen4api.service;

import com.bettersoft.nextgen4api.config.exception.AppErrors;
import com.bettersoft.nextgen4api.config.exception.BusinessException;
import com.bettersoft.nextgen4api.repository.UserRepository;
import com.bettersoft.nextgen4api.rest.payload.request.RotaRequest;
import com.bettersoft.nextgen4api.rest.payload.request.UserRequest;
import com.bettersoft.nextgen4api.service.generic.CrudService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService implements CrudService<UserRequest> {

    private final UserRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public void validate(final boolean toPersist, final UserRequest request) {

    }

}
