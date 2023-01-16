package com.bettersoft.nextgen4api.service;

import com.bettersoft.nextgen4api.config.exception.AppErrors;
import com.bettersoft.nextgen4api.config.exception.BusinessException;
import com.bettersoft.nextgen4api.repository.AccountOperationRepository;
import com.bettersoft.nextgen4api.rest.payload.request.AccountOperationRequest;
import com.bettersoft.nextgen4api.service.generic.CrudService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AccountOperationService implements CrudService<AccountOperationRequest> {

    private final AccountOperationRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public void validate(final boolean toPersist, final AccountOperationRequest request) {
        if(toPersist) {
            if(Objects.isNull(request.getUuid())) {
                repository.findByName(request.getName())
                        .ifPresent(rota -> { throw new BusinessException(AppErrors.duplicated("Nome", request.getName())); });
            } else {
                repository.findByName(request.getName())
                        .ifPresent(rota -> {
                            if(!Objects.equals(rota.getUuid(), request.getUuid())) {
                                throw new BusinessException(AppErrors.duplicated("Nome", request.getName()));
                            }
                        });
            }
        }
    }

}
