package com.bettersoft.nextgen4api.search.model.impl;

import com.bettersoft.nextgen4api.model.ContractModel;
import com.bettersoft.nextgen4api.search.model.GenericSearch;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Setting;

import javax.validation.constraints.NotNull;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

@Getter
@Setter
@Document(indexName = "contract_model")
@Setting(settingPath = "/search/bs-analyzer.json")
public class ContractModelSearch extends GenericSearch<ContractModelSearch, ContractModel> {

    private Long price;

    @NotNull
    private Integer qtdQuotes;

    private Long quotePrice;

    @Field(type = Text, analyzer = "bs_filter", searchAnalyzer = "bs_filter")
    private String description;

    @Override
    public ContractModelSearch toSearch(ModelMapper modelMapper, ContractModel entity) {
        return modelMapper.map(entity, ContractModelSearch.class);
    }

//    @Field(type = FieldType.Nested, includeInParent = true)
//    private List<Author> authors;

    // standard getters and setters
}
