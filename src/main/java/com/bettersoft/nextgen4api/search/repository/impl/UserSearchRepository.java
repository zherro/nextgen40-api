package com.bettersoft.nextgen4api.search.repository.impl;

import com.bettersoft.nextgen4api.search.model.impl.RotaSearch;
import com.bettersoft.nextgen4api.search.model.impl.UserSearch;
import com.bettersoft.nextgen4api.search.repository.GenericSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;

public interface UserSearchRepository extends GenericSearchRepository<UserSearch> {

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
            "                \"username\": \".*?0.*\"" +
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
            "                \"email\": \".*?0.*\"" +
            "              }" +
            "            }" +
            "          ]" +
            "        }" +
            "      }" +
            "    ]" +
            "  }" +
            "}")
    Page<UserSearch> findByStringFilter(String filter, Pageable pageable);
}
