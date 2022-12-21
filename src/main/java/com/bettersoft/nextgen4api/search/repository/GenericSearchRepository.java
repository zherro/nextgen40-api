package com.bettersoft.nextgen4api.search.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GenericSearchRepository<S> extends ElasticsearchRepository<S, String> {

    Page<S> findById(String id, Pageable pageable);

    Page<S> findByStringFilter(String filter, Pageable pageable);

}