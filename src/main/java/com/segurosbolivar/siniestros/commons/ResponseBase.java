package com.segurosbolivar.siniestros.commons;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
public class ResponseBase {
    public ResponseBase(BigDecimal op_Resultado, String Op_MSG) {
        this.Op_Resultado = op_Resultado;
        this.Op_MSG = Op_MSG;
    }

    private BigDecimal Op_Resultado;
    private String Op_MSG;
}
