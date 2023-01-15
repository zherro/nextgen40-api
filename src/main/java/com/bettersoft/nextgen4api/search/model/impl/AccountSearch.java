package com.bettersoft.nextgen4api.search.model.impl;

import com.bettersoft.nextgen4api.model.Account;
import com.bettersoft.nextgen4api.model.Rota;
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
@Document(indexName = "account")
@Setting(settingPath = "/search/bs-analyzer.json")
public class AccountSearch extends GenericSearch<AccountSearch, Account> {

    @Field(type = Text, analyzer = "bs_filter", searchAnalyzer = "bs_filter")
    private String name;

    @Field(type = Text, analyzer = "bs_filter", searchAnalyzer = "bs_filter")
    private String description;

    @Override
    public AccountSearch toSearch(ModelMapper modelMapper, Account entity) {
        return modelMapper.map(entity, AccountSearch.class);
    }

//    @Field(type = FieldType.Nested, includeInParent = true)
//    private List<Author> authors;

    // standard getters and setters
}
