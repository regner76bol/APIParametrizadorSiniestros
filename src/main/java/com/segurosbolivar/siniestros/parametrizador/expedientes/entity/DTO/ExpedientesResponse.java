package com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ExpedientesResponse extends ResponseBase {
    public ExpedientesResponse(BigDecimal Op_ExpxCia, BigDecimal Op_ExpxProd, Object OpcurExped){
        this.Op_ExpxCia=Op_ExpxCia;
        this.Op_ExpxProd=Op_ExpxProd;
        this.OpcurExped = OpcurExped;

    }
    private BigDecimal Op_ExpxCia;
    private BigDecimal Op_ExpxProd;
    private Object OpcurExped;
}
