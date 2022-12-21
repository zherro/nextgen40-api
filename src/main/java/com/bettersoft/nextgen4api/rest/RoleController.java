package com.bettersoft.nextgen4api.rest;

import com.bettersoft.nextgen4api.config.Const;
import com.bettersoft.nextgen4api.model.Role;
import com.bettersoft.nextgen4api.repository.RoleRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@SecurityRequirement(name = "nextgenapi")
@CrossOrigin(origins = "*", maxAge = Const.MAX_AGE)
@RestController
@RequestMapping("/api/roles")
public class RoleController {

//    private final IAuthenticationFacade authenticationFacade;

    private final RoleRepository repository;

    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Role> getPage(){
        return repository.findAll();
    }
}
