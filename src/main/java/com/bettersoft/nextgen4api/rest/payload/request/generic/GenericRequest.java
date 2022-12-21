package com.bettersoft.nextgen4api.rest.payload.request.generic;

import com.bettersoft.nextgen4api.model.BaseEntity;
import com.bettersoft.nextgen4api.model.Rota;
import com.bettersoft.nextgen4api.rest.payload.request.RotaRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Objects;

public interface GenericRequest<R, E extends BaseEntity> {
    E toEntity(final ModelMapper modelMapper, final R request);
    R toRequest(final ModelMapper modelMapper, final E entity);

    E merge(final ModelMapper modelMapper, final E entity, final R request);

    default void skipMapIds(final ModelMapper modelMapper, Class<R> tClass, Class<E> eClass) {
        TypeMap<R, E> typeMap = modelMapper.getTypeMap(tClass, eClass);

        if(Objects.isNull(typeMap)) {
            TypeMap<R, E> propertyMapper = modelMapper.createTypeMap(tClass, eClass);
            propertyMapper.addMappings(mapper -> mapper.skip(E::setId));
            propertyMapper.addMappings(mapper -> mapper.skip(E::setUuid));
        }
    }

    default void mergeRequest(final ModelMapper modelMapper, final R request, final E entity) {
        modelMapper.map(request, entity);
    }
}
