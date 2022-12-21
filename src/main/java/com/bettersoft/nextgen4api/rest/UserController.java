package com.bettersoft.nextgen4api.rest;

import com.bettersoft.nextgen4api.config.Const;
import com.bettersoft.nextgen4api.config.exception.BusinessException;
import com.bettersoft.nextgen4api.facade.IAuthenticationFacade;
import com.bettersoft.nextgen4api.model.Rota;
import com.bettersoft.nextgen4api.model.User;
import com.bettersoft.nextgen4api.model.error.ErrorDetails;
import com.bettersoft.nextgen4api.repository.UserRepository;
import com.bettersoft.nextgen4api.rest.generic.GenericController;
import com.bettersoft.nextgen4api.rest.payload.request.UserRequest;
import com.bettersoft.nextgen4api.rest.payload.response.UserResponse;
import com.bettersoft.nextgen4api.rest.payload.response.generic.GenericResponse;
import com.bettersoft.nextgen4api.search.filter.RotaFilter;
import com.bettersoft.nextgen4api.search.model.impl.RotaSearch;
import com.bettersoft.nextgen4api.search.model.impl.UserSearch;
import com.bettersoft.nextgen4api.search.repository.impl.UserSearchRepository;
import com.bettersoft.nextgen4api.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


@SecurityRequirement(name = "nextgenapi")
@CrossOrigin(origins = "*", maxAge = Const.MAX_AGE)
@RestController
@RequestMapping("/api/users")
public class UserController extends GenericController<UserSearch, User, UserRequest, UserResponse> {

    private final IAuthenticationFacade authenticationFacade;

    public UserController(
            UserRepository repository,
            UserSearchRepository searchRepository,
            IAuthenticationFacade authenticationFacade,
            ModelMapper modelMapper,
            UserService service
    ) {
        super(
                repository,
                searchRepository,
                modelMapper,
                authenticationFacade,
                service);
        this.authenticationFacade = authenticationFacade;
    }

    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public ResponseEntity<Page<GenericResponse>> getPage(RotaFilter filter, Pageable pageable){
        return super.getPageBySearch(Objects.nonNull(filter) ? filter.getFilter() : "", pageable);
    }

    @Override
    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public ResponseEntity<GenericResponse<User, UserResponse>> getOne(@PathVariable String uuid){
        return super.getOne(uuid);
    }

    @Override
    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{uuid}")
    public ResponseEntity<UserResponse> update(@PathVariable String uuid, @RequestBody UserRequest updated){
        return super.update(uuid, updated);
    }

    @Override
    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request){
        if(Objects.isNull(request.getPassword()) || request.getPassword().trim().equals("")) {
            throw new BusinessException(new ErrorDetails(HttpStatus.BAD_REQUEST, "FIELD_REQUIRED", "Required Field", "Informe uma senha"));
        }
        return super.create(request);
    }

    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{uuid}")
    public ResponseEntity<UserResponse> delete(@PathVariable String uuid){
       return super.delete(uuid, UserRequest.class);
    }

}
