package com.bettersoft.nextgen4api.model;

import com.bettersoft.nextgen4api.search.model.impl.RotaSearch;
import com.bettersoft.nextgen4api.rest.payload.response.RotaResponse;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(of = {"name"}, callSuper=false)
@Table(	name = "rotas",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
        })
@Entity
public class Rota extends BaseEntity<RotaSearch, RotaResponse> {

    @NotNull
    private String name;

    private String description;

    @Override
    public String entityName() {
        return "Rota";
    }

    @Override
    public RotaResponse toResponse(ModelMapper mapper) {
        return (new RotaResponse()).toResponse(mapper, this);
    }

    @Override
    public RotaSearch toSearchModel(ModelMapper mapper) {
        return (new RotaSearch()).toSearch(mapper, this);
    }
}
