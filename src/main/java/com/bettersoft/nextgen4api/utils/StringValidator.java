package com.bettersoft.nextgen4api.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public interface StringValidator {

    static String stripAccent(final String value) {
        return Objects.isNull(value) ? null : StringUtils.stripAccents(value.toLowerCase());
    }
}
