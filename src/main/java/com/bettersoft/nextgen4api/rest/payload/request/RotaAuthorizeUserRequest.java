package com.bettersoft.nextgen4api.rest.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RotaAuthorizeUserRequest {

    private String user;
    private List<String> routes;
}
