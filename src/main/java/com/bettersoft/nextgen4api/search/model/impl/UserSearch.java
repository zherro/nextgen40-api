package com.bettersoft.nextgen4api.search.model.impl;

import com.bettersoft.nextgen4api.model.User;
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
@Document(indexName = "user")
@Setting(settingPath = "/search/bs-analyzer.json")
public class UserSearch extends GenericSearch<UserSearch, User> {

    @Field(type = Text, analyzer = "bs_filter", searchAnalyzer = "bs_filter")
    private String username;

    @Field(type = Text, analyzer = "bs_filter", searchAnalyzer = "bs_filter")
    private String email;

    @Override
    public UserSearch toSearch(ModelMapper modelMapper, User entity) {
        return modelMapper.map(entity, UserSearch.class);
    }

//    @Field(type = FieldType.Nested, includeInParent = true)
//    private List<Author> authors;

    // standard getters and setters
}
