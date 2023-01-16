package com.bettersoft.nextgen4api.rest;

import com.bettersoft.nextgen4api.config.Const;
import com.bettersoft.nextgen4api.facade.IAuthenticationFacade;
import com.bettersoft.nextgen4api.model.ContractModel;
import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.repository.ContractModelRepository;
import com.bettersoft.nextgen4api.rest.generic.GenericController;
import com.bettersoft.nextgen4api.rest.payload.request.ContractModelRequest;
import com.bettersoft.nextgen4api.rest.payload.response.ContractModelResponse;
import com.bettersoft.nextgen4api.rest.payload.response.generic.GenericResponse;
import com.bettersoft.nextgen4api.search.filter.generic.GenericFilter;
import com.bettersoft.nextgen4api.search.model.impl.ContractModelSearch;
import com.bettersoft.nextgen4api.search.repository.impl.ContractModelSearchRepository;
import com.bettersoft.nextgen4api.service.ContractModelService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@SecurityRequirement(name = "nextgenapi")
@CrossOrigin(origins = "*", maxAge = Const.MAX_AGE)
@RestController
@RequestMapping("/api/contract-model")
public class ContractModelController extends GenericController<ContractModelSearch, ContractModel, ContractModelRequest, ContractModelResponse> {

    private final IAuthenticationFacade authenticationFacade;
    private final ContractModelRepository repository;
    private final ModelMapper modelMapper;

    public ContractModelController(
            ContractModelRepository repository,
            ContractModelSearchRepository searchRepository,
            IAuthenticationFacade authenticationFacade,
            ModelMapper modelMapper,
            ContractModelService service
    ) {
        super(
                repository,
                searchRepository,
                modelMapper,
                authenticationFacade,
                service);
        this.repository = repository;
        this.authenticationFacade = authenticationFacade;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public ResponseEntity<Page<GenericResponse>> getPage(GenericFilter filter, Pageable pageable) {
        return super.getPageBySearch(Objects.nonNull(filter) ? filter.getFilter() : "", pageable);
    }

    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/actives")
    public List<?> getActives() {
        var routes = repository.findAllByStatus(Status.ACTIVE);
        return routes.stream()
                .filter(route -> Status.ACTIVE.equals(route.getStatus()))
                .map(route -> route.toResponse(modelMapper)).toList();
    }

    @Override
    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public ResponseEntity getOne(@PathVariable String uuid) {
        return super.getOne(uuid);
    }

    @Override
    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{uuid}")
    public ResponseEntity update(@PathVariable String uuid, @RequestBody ContractModelRequest updated) {
        return super.update(uuid, updated);
    }

    @Override
    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ResponseEntity create(@RequestBody ContractModelRequest request) {
        return super.create(request);
    }

    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable String uuid) {
        return super.delete(uuid, ContractModelRequest.class);
    }

}
