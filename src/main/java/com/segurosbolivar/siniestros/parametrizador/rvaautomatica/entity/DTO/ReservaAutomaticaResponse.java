package com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ReservaAutomaticaResponse extends ResponseBase {

    private Object opcurRva;

    public ReservaAutomaticaResponse(Object opcurRva){
        this.opcurRva=opcurRva;
    }


}
