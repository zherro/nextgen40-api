package com.bettersoft.nextgen4api.search.model.impl;

import com.bettersoft.nextgen4api.model.Account;
import com.bettersoft.nextgen4api.model.AccountOperation;
import com.bettersoft.nextgen4api.search.model.GenericSearch;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Setting;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

@Getter
@Setter
@Document(indexName = "account_operation")
@Setting(settingPath = "/search/bs-analyzer.json")
public class AccountOperationSearch extends GenericSearch<AccountOperationSearch, AccountOperation> {

    @Field(type = Text, analyzer = "bs_filter", searchAnalyzer = "bs_filter")
    private String name;

    @Field(type = Text, analyzer = "bs_filter", searchAnalyzer = "bs_filter")
    private String description;

    @Override
    public AccountOperationSearch toSearch(ModelMapper modelMapper, AccountOperation entity) {
        return modelMapper.map(entity, AccountOperationSearch.class);
    }
}
