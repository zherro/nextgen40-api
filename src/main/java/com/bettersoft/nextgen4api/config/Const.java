package com.bettersoft.nextgen4api.config;

public interface Const {

    long MAX_AGE = 3600L;

    String GRANT_FOR_ALL = "hasRole('USER') or hasRole('MODERATOR') or hasRole('ROLE_ADMIN') or hasRole('ADMIN')";

    String GRANT_ADMIN= "hasRole('ROLE_ADMIN')";

}