package com.bettersoft.nextgen4api.rest.payload.response;

import com.bettersoft.nextgen4api.model.Rota;
import com.bettersoft.nextgen4api.rest.payload.response.generic.GenericResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class RotaResponse extends GenericResponse<Rota, RotaResponse> {

    private String name;
    private String description;

    @Override
    public RotaResponse toResponse(final ModelMapper modelMapper, final Rota entity) {
        return modelMapper.map(entity, RotaResponse.class);
    }
}
