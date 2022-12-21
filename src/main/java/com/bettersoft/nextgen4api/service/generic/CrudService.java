package com.bettersoft.nextgen4api.service.generic;

import com.bettersoft.nextgen4api.rest.payload.request.generic.GenericRequest;

public interface CrudService<T extends GenericRequest> {

    void validate(boolean isToPersist, T request);
}
