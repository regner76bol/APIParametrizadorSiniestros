package com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ListarConsecuenciasResponse extends ResponseBase {
    private Object opCurCons;
    public ListarConsecuenciasResponse(Object opCurCons){
        this.opCurCons=opCurCons;
    }
}
