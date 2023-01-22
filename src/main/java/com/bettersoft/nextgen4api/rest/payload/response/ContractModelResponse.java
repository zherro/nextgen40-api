package com.bettersoft.nextgen4api.rest.payload.response;

import com.bettersoft.nextgen4api.model.ContractModel;
import com.bettersoft.nextgen4api.model.enums.SimNaoEnum;
import com.bettersoft.nextgen4api.model.enums.TipoContratoEnum;
import com.bettersoft.nextgen4api.rest.payload.response.generic.GenericResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class ContractModelResponse extends GenericResponse<ContractModel, ContractModelResponse> {

    private Long price;

    private Integer qtdQuotes;

    private Long quotePrice;

    private String description;

    private TipoContratoEnum tipoContrato;

    private SimNaoEnum onWeekend;

    @Override
    public ContractModelResponse toResponse(final ModelMapper modelMapper, final ContractModel entity) {
        return modelMapper.map(entity, ContractModelResponse.class);
    }
}
