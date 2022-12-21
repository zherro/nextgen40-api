package com.bettersoft.nextgen4api.model.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

@Getter
@Builder
@AllArgsConstructor
public class ErrorDetails {

    public ErrorDetails(Supplier<ErrorDetails> errorSupplier) {
        this.status = errorSupplier.get().getStatus();
        this.code = errorSupplier.get().getCode();
        this.title = errorSupplier.get().getTitle();
        this.detail = errorSupplier.get().getDetail();
    }

    public ErrorDetails(HttpStatus status, String code, String title, String detail) {
        this.status = status.value();
        this.code = code;
        this.title = title;
        this.detail = detail;
    }

    private Integer status;
    private String code;
    private String title;
    private String detail;

}
