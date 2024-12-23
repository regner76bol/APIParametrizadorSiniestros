package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
public class CcambioResponse {
    public CcambioResponse(Object Op_CurCCambio, BigDecimal Op_Resultado, String Op_MSG){
        this.Op_CurCCambio=Op_CurCCambio;
        this.Op_Resultado=Op_Resultado;
        this.Op_MSG = Op_MSG;
    }
    private Object Op_CurCCambio;
    private BigDecimal Op_Resultado;
    private String Op_MSG;


}
