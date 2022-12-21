package com.bettersoft.nextgen4api.config.exception;

import com.bettersoft.nextgen4api.model.error.ErrorDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {

    private final ErrorDetails errorDetail;

}
