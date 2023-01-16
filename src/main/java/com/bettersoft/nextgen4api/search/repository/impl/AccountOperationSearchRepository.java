package com.bettersoft.nextgen4api.search.repository.impl;

import com.bettersoft.nextgen4api.search.model.impl.AccountOperationSearch;
import com.bettersoft.nextgen4api.search.repository.GenericSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;

public interface AccountOperationSearchRepository extends GenericSearchRepository<AccountOperationSearch> {

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
            "                \"name\": \".*?0.*\"" +
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
    Page<AccountOperationSearch> findByStringFilter(String filter, Pageable pageable);
}
