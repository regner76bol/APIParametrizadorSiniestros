package com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListarConsecuenciasResponse extends ResponseBase {
    private Object opCurCons;
    public ListarConsecuenciasResponse(Object opCurCons){
        this.opCurCons=opCurCons;
    }
}
