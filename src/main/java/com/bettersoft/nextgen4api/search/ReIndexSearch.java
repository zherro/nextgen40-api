package com.bettersoft.nextgen4api.search;

import com.bettersoft.nextgen4api.model.BaseEntity;
import com.bettersoft.nextgen4api.repository.generic.GenericRepository;
import com.bettersoft.nextgen4api.search.repository.GenericSearchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ReIndexSearch<E extends BaseEntity, S> {

    private final GenericRepository<E> repository;
    private final GenericSearchRepository<S> searchRepository;
    private final ModelMapper modelMapper;

    public ReIndexSearch(
            GenericRepository<E> repository,
            GenericSearchRepository<S> searchRepository,
            ModelMapper modelMapper
    ) {
        this.repository = repository;
        this.searchRepository = searchRepository;
        this.modelMapper = modelMapper;
    }

    public void reindexAll() {

        Page<E> page = reindexData(0, 100);

        for (int i = 1; i < page.getTotalPages(); i++) {
            reindexData(i, 100);
        }
    }

    private Page<E> reindexData(final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size);
        var pageContent = repository.findAll(pageable);

        pageContent.getContent().forEach(data -> {
            var search = data.toSearchModel(modelMapper);
            searchRepository.save((S) search);
        });
        return pageContent;
    }
}
