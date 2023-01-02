package com.bettersoft.nextgen4api.rest.payload.response;

import com.bettersoft.nextgen4api.model.User;
import com.bettersoft.nextgen4api.rest.payload.response.generic.GenericResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserResponse  extends GenericResponse<User, UserResponse> {

    private String username;
    private String email;

    private Set<RotaResponse> rotas;

    @Override
    public UserResponse toResponse(ModelMapper modelMapper, User entity) {
        return modelMapper.map(entity, UserResponse.class);
    }
}
