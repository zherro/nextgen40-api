package com.bettersoft.nextgen4api.facade.impl;

import com.bettersoft.nextgen4api.facade.IAuthenticationFacade;
import com.bettersoft.nextgen4api.model.User;
import com.bettersoft.nextgen4api.repository.generic.GenericRepository;
import com.bettersoft.nextgen4api.service.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getUser(GenericRepository<?> repository) {
        var user = (UserDetailsImpl) getAuthentication().getPrincipal();
        return repository.getUserById(user.getId());
    }
}