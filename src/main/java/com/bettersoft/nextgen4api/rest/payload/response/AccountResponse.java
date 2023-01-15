package com.bettersoft.nextgen4api.rest.payload.response;

import com.bettersoft.nextgen4api.model.Account;
import com.bettersoft.nextgen4api.rest.payload.response.generic.GenericResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class AccountResponse extends GenericResponse<Account, AccountResponse> {

    private String name;
    private String description;

    @Override
    public AccountResponse toResponse(final ModelMapper modelMapper, final Account entity) {
        return modelMapper.map(entity, AccountResponse.class);
    }
}
