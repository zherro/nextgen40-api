package com.bettersoft.nextgen4api.model;

import com.bettersoft.nextgen4api.rest.payload.response.AccountResponse;
import com.bettersoft.nextgen4api.rest.payload.response.ContractModelResponse;
import com.bettersoft.nextgen4api.search.model.impl.AccountSearch;
import com.bettersoft.nextgen4api.search.model.impl.ContractModelSearch;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Table(	name = "contract_model")
@Entity
public class ContractModel extends BaseEntity<ContractModelSearch, ContractModelResponse> {

    @NotNull
    private Long price;

    @NotNull
    private Integer qtdQuotes;

    @NotNull
    private Long quotePrice;

    private String description;

    @Override
    public String entityName() {
        return "Modelo Contrato";
    }

    @Override
    public ContractModelResponse toResponse(ModelMapper mapper) {
        return (new ContractModelResponse()).toResponse(mapper, this);
    }

    @Override
    public ContractModelSearch toSearchModel(ModelMapper mapper) {
        return (new ContractModelSearch()).toSearch(mapper, this);
    }
}
