package com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CausasResponse extends ResponseBase {
    public CausasResponse(Integer Op_CauxCia,Integer Op_CauxSecc,Integer Op_CauxProd){
        this.Op_CauxCia= Op_CauxCia;
        this.Op_CauxSecc=Op_CauxSecc;
        this.Op_CauxProd=Op_CauxProd;
    }
    private Integer Op_CauxCia;
    private Integer Op_CauxSecc;
    private Integer Op_CauxProd;
}
