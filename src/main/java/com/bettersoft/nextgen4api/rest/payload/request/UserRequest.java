package com.bettersoft.nextgen4api.rest.payload.request;

import com.bettersoft.nextgen4api.model.User;
import com.bettersoft.nextgen4api.model.enums.Status;
import com.bettersoft.nextgen4api.rest.payload.request.generic.GenericRequest;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class UserRequest implements GenericRequest<UserRequest, User> {

    private String uuid;

    @NotNull
    private String username;
    private String email;
    private String password;

    @NotNull
    private Status status;


    @Override
    public User toEntity(final ModelMapper modelMapper, final UserRequest request) {
        return modelMapper.map(request, User.class);
    }

    @Override
    public UserRequest toRequest(final ModelMapper modelMapper, final User entity) {
        return modelMapper.map(entity, UserRequest.class);
    }

    @Override
    public User merge(final ModelMapper modelMapper, final User entity, final UserRequest request) {
        skipMapIds(modelMapper, UserRequest.class, User.class);
        var currentPassword = entity.getPassword();
        mergeRequest(modelMapper, request, entity);

        if(Objects.isNull(request.getPassword()) || request.getPassword().trim().equals("")) {
            entity.setPassword(currentPassword);
        }
        return  entity;
    }

}
