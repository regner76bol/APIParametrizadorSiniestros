package com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListarCausasResponse extends ResponseBase {
    private Object opCurCausas;

    public ListarCausasResponse(Object opCurCausas ){
        this.opCurCausas=opCurCausas;
    }
}
