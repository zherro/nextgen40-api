package com.bettersoft.nextgen4api.model;

import com.bettersoft.nextgen4api.model.enums.OperationType;
import com.bettersoft.nextgen4api.rest.payload.response.AccountOperationResponse;
import com.bettersoft.nextgen4api.search.model.impl.AccountOperationSearch;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(of = {"name"}, callSuper=false)
@Table(	name = "account_operation",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
        })
@Entity
public class AccountOperation extends BaseEntity<AccountOperationSearch, AccountOperationResponse> {

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private OperationType operationType;

    private String description;

    @Override
    public String entityName() {
        return "Operacao Conta";
    }

    @Override
    public AccountOperationResponse toResponse(ModelMapper mapper) {
        return (new AccountOperationResponse()).toResponse(mapper, this);
    }

    @Override
    public AccountOperationSearch toSearchModel(ModelMapper mapper) {
        return (new AccountOperationSearch()).toSearch(mapper, this);
    }
}
