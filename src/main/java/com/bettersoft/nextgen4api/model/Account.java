package com.bettersoft.nextgen4api.model;

import com.bettersoft.nextgen4api.rest.payload.response.AccountResponse;
import com.bettersoft.nextgen4api.search.model.impl.AccountSearch;
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
@Table(	name = "account",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
        })
@Entity
public class Account extends BaseEntity<AccountSearch, AccountResponse> {

    @NotNull
    private String name;

    private String description;

    @Override
    public String entityName() {
        return "Conta";
    }

    @Override
    public AccountResponse toResponse(ModelMapper mapper) {
        return (new AccountResponse()).toResponse(mapper, this);
    }

    @Override
    public AccountSearch toSearchModel(ModelMapper mapper) {
        return (new AccountSearch()).toSearch(mapper, this);
    }
}
