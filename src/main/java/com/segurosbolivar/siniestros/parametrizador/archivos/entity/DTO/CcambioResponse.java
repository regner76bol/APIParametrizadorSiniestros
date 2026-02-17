package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CcambioResponse extends ResponseBase {

    private Object Op_CurCCambio;


    public CcambioResponse(Object Op_CurCCambio){
        this.Op_CurCCambio=Op_CurCCambio;

    }



}
