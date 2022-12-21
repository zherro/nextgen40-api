package com.bettersoft.nextgen4api.search.model;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public abstract class GenericSearch<S, E> {

    @Id
    protected String id;

    public abstract S toSearch(ModelMapper modelMapper, E entity);
}
