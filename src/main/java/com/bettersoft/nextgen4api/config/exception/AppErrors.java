package com.bettersoft.nextgen4api.config.exception;

import com.bettersoft.nextgen4api.model.error.ErrorDetails;
import org.springframework.http.HttpStatus;

public interface AppErrors {

    static ErrorDetails notFound (final String resourceName, final String identifier) {
        return new ErrorDetails(
                HttpStatus.NOT_FOUND,
                "NOT_FOUND",
                "",
                String.format("%s nao encontrado(a) com o indetificador: %s", resourceName, identifier));
    }

    static ErrorDetails duplicated (final String filedName, final String value) {
        return new ErrorDetails(
                HttpStatus.CONFLICT,
                "DUPLICATED",
                "",
                String.format("%s : '%s' ja esta em uso!", filedName, value));
    }

    static ErrorDetails isDeleted (final String filedName, final String value) {
        return new ErrorDetails(
                HttpStatus.CONFLICT,
                "DELETED",
                "Is deleted",
                String.format("%s com identificador: '%s' foi removido(a)!", filedName, value));
    }

    static ErrorDetails accessDenied () {
        return new ErrorDetails(
                HttpStatus.UNAUTHORIZED,
                "ACCESS_DENIED",
                "",
                "Acesso negado!");
    }

}