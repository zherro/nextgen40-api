package com.bettersoft.nextgen4api.rest.payload.response;

import com.bettersoft.nextgen4api.model.AccountOperation;
import com.bettersoft.nextgen4api.model.enums.OperationType;
import com.bettersoft.nextgen4api.rest.payload.response.generic.GenericResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class AccountOperationResponse extends GenericResponse<AccountOperation, AccountOperationResponse> {

    private String name;
    private String description;
    private OperationType operationType;

    @Override
    public AccountOperationResponse toResponse(final ModelMapper modelMapper, final AccountOperation entity) {
        return modelMapper.map(entity, AccountOperationResponse.class);
    }
}
