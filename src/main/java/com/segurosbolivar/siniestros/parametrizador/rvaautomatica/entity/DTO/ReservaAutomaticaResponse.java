package com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ReservaAutomaticaResponse extends ResponseBase {

    private Object opcurRva;

    public ReservaAutomaticaResponse(Object opcurRva){
        this.opcurRva=opcurRva;
    }


}
