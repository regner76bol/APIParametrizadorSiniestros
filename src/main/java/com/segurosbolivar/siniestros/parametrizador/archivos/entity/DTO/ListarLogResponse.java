package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
public class ListarLogResponse {
    public ListarLogResponse(Object Op_curLog, BigDecimal Op_Resultado, String Op_MSG){
        this.Op_curLog=Op_curLog;
        this.Op_Resultado = Op_Resultado;
        this.Op_MSG = Op_MSG;
    }
    private Object Op_curLog;
    private BigDecimal Op_Resultado;
    private String Op_MSG;
}
