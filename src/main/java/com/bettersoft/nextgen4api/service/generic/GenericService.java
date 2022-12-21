package com.bettersoft.nextgen4api.service.generic;

import com.bettersoft.nextgen4api.config.exception.AppErrors;
import com.bettersoft.nextgen4api.config.exception.BusinessException;
import com.bettersoft.nextgen4api.model.BaseEntity;
import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.search.model.GenericSearch;
import com.bettersoft.nextgen4api.repository.generic.GenericRepository;
import com.bettersoft.nextgen4api.search.repository.GenericSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public abstract class GenericService<E extends BaseEntity, S extends GenericSearch> {

    private final GenericRepository<E> repository;

    private final GenericSearchRepository<S> repositorySearch;

    protected GenericService(GenericRepository<E> repository, GenericSearchRepository<S> repositorySearch) {
        this.repository = repository;
        this.repositorySearch = repositorySearch;
    }

    public Page<E> getPage(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Page<E> getPageBySearch(String filter, Pageable pageable) {
        Page<S> result = repositorySearch.findByStringFilter(filter, pageable);
        List<E> response = repository.findAllByIdIn(result.getContent().stream().map(s -> Long.valueOf(s.getId())).toList());
        return new PageImpl<>(response, result.getPageable(), result.getTotalElements());
    }

    public E get(String uuid){
        return repository.findByUuid(uuid).orElseThrow(
                () -> new BusinessException(AppErrors.notFound("Registro", uuid))
        );
    }

    @Transactional
    public E update(E updated){
        return repository.save(updated);
    }

    @Transactional
    public E create(E data, S search){
        E entity = repository.save(data);
        if(Objects.nonNull(search)) {
            search.setId(String.valueOf(entity.getId()));
            repositorySearch.save(search);
        }
        return entity;
    }

    @Transactional
    public void delete(String id){
        //check if object with this id exists
        E data = get(id);
        data.setStatus(Status.DELETED);
        data.setDeletedAt(LocalDateTime.now());
        repository.save(data);
    }
}