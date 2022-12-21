package com.bettersoft.nextgen4api.rest.generic;

import com.bettersoft.nextgen4api.config.Const;
import com.bettersoft.nextgen4api.config.exception.AppErrors;
import com.bettersoft.nextgen4api.config.exception.BusinessException;
import com.bettersoft.nextgen4api.facade.IAuthenticationFacade;
import com.bettersoft.nextgen4api.model.BaseEntity;
import com.bettersoft.nextgen4api.model.User;
import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.search.ReIndexSearch;
import com.bettersoft.nextgen4api.search.model.GenericSearch;
import com.bettersoft.nextgen4api.repository.generic.GenericRepository;
import com.bettersoft.nextgen4api.search.repository.GenericSearchRepository;
import com.bettersoft.nextgen4api.search.filter.generic.GenericFilter;
import com.bettersoft.nextgen4api.rest.payload.request.generic.GenericRequest;
import com.bettersoft.nextgen4api.rest.payload.response.generic.GenericResponse;
import com.bettersoft.nextgen4api.service.generic.CrudService;
import com.bettersoft.nextgen4api.service.generic.GenericService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public abstract class GenericController<S extends GenericSearch,E extends BaseEntity, A extends GenericRequest<A, E>, R extends GenericResponse<E,  R> > {

    private final GenericService<E, S> service;
    private final ModelMapper modelMapper;
    private final IAuthenticationFacade authenticationFacade;
    private final GenericRepository<E> repository;
    private final GenericSearchRepository<S> repositorySearch;
    private final CrudService<A> crudService;
    protected GenericController(
                                GenericRepository<E> repository,
                                GenericSearchRepository<S> repositorySearch,
                                ModelMapper mapper,
                                IAuthenticationFacade authenticationFacade,
                                CrudService<A> crudService) {
        this.service = new GenericService<E, S>(repository, repositorySearch) {};
        this.modelMapper = mapper;
        this.authenticationFacade = authenticationFacade;
        this.repository = repository;
        this.repositorySearch = repositorySearch;
        this.crudService = crudService;
    }

    public ResponseEntity<Page<GenericResponse>> getPage(Pageable pageable){
       var result = service.getPage(pageable);
        List<GenericResponse> responses = result.getContent().stream()
                .map(entity -> entity.toResponse(modelMapper))
                .toList();
        var response = new PageImpl<>(responses, result.getPageable(), result.getTotalElements());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Page<GenericResponse>> getPageBySearch(String filter, Pageable pageable){
        Page<E> result = service.getPageBySearch(filter, pageable);
        List<GenericResponse> responses = result.getContent().stream()
                .map(entity -> entity.toResponse(modelMapper))
                .toList();
        var response = new PageImpl<>(responses, result.getPageable(), result.getTotalElements());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<GenericResponse<E, R>> getOne(String uuid){
        E entity = service.get(uuid);
        return ResponseEntity.ok(entity.toResponse(modelMapper));
    }

    public ResponseEntity<R> update(String uuid, A request){
        E entity = service.get(uuid);
        crudService.validate(true, request);
        E updated = request.merge(modelMapper, entity, request);
        entity.setUpdatedBy(getUser());
        service.update(updated);
        return ResponseEntity.ok((R) entity.toResponse(modelMapper));
    }

    public ResponseEntity<R> create(A request){
        crudService.validate(true, request);
        var entity = request.toEntity(modelMapper, request);
        var search = entity.toSearchModel(modelMapper);
        entity.setCreatedBy(getUser());
        service.create(entity, Objects.nonNull(search) ? (S) search : null);

        return ResponseEntity.ok((R) entity.toResponse(modelMapper));
    }

    public ResponseEntity<R> delete(final String uuid, Class<A> requestClass) {
        E entity = service.get(uuid);
        if(Status.DELETED.equals(entity.getStatus())) {
            throw new BusinessException(AppErrors.isDeleted(entity.entityName(), uuid));
        }
        crudService.validate(false, modelMapper.map(entity, requestClass));

        entity.setDeletedBy(getUser());
        entity.setStatus(Status.DELETED);
        entity.setDeletedAt(LocalDateTime.now());

        E updated = service.update(entity);
        return ResponseEntity.ok((R) updated.toResponse(modelMapper));
    }

    private User getUser() {
        if(authenticationFacade != null) {
            return authenticationFacade.getUser(repository);
        }
        return null;
    }

    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/re-index")
    public ResponseEntity reIndexData() {
        var reindexSearch = new ReIndexSearch<E, S>(repository, repositorySearch, modelMapper);
        reindexSearch.reindexAll();
        return ResponseEntity.ok().build();
    }
}