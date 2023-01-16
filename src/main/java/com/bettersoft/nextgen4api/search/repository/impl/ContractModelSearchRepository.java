package com.bettersoft.nextgen4api.search.repository.impl;

import com.bettersoft.nextgen4api.search.model.impl.ContractModelSearch;
import com.bettersoft.nextgen4api.search.repository.GenericSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;

public interface ContractModelSearchRepository extends GenericSearchRepository<ContractModelSearch> {

//    @Query("{\"bool\": {\"must\": {\"match\": {\"title\": \"?0\"}}, \"filter\": {\"term\": {\"tags\": \"?1\" }}}}")

    @Query("{" +
            "  \"bool\": {" +
            "    \"should\": [" +
            "      {" +
            "        \"bool\": {" +
            "          \"must\": [" +
            "            {" +
            "              \"regexp\": {" +
            "                \"id\": \".*?0.*\"" +
            "              }" +
            "            }" +
            "          ]" +
            "        }" +
            "      }," +
            "      {" +
            "        \"bool\": {" +
            "          \"must\": [" +
            "            {" +
            "              \"regexp\": {" +
            "                \"price\": \".*?0.*\"" +
            "              }" +
            "            }" +
            "          ]" +
            "        }" +
            "      }," +
            "      {" +
            "        \"bool\": {" +
            "          \"must\": [" +
            "            {" +
            "              \"regexp\": {" +
            "                \"quotePrice\": \".*?0.*\"" +
            "              }" +
            "            }" +
            "          ]" +
            "        }" +
            "      }," +
            "      {" +
            "        \"bool\": {" +
            "          \"must\": [" +
            "            {" +
            "              \"regexp\": {" +
            "                \"description\": \".*?0.*\"" +
            "              }" +
            "            }" +
            "          ]" +
            "        }" +
            "      }" +
            "    ]" +
            "  }" +
            "}")
    Page<ContractModelSearch> findByStringFilter(String filter, Pageable pageable);
}
