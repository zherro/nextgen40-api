package com.bettersoft.nextgen4api.search.filter.generic;

import lombok.Setter;

import java.util.Objects;

@Setter
public abstract class GenericFilter {

    protected String filter;

    public String getFilter() {
        return Objects.nonNull(filter) ? filter : "";
    }
}
