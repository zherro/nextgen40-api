package com.bettersoft.nextgen4api.facade;

import com.bettersoft.nextgen4api.model.User;
import com.bettersoft.nextgen4api.repository.generic.GenericRepository;
import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();

    User getUser(GenericRepository<?> repository);
}