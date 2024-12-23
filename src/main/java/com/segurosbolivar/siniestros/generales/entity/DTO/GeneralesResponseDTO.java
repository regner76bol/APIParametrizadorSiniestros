package com.segurosbolivar.siniestros.generales.entity.DTO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class GeneralesResponseDTO extends ResponseBase {
    private Object OpCurCia;
    private Object OpCurSecc;
    private Object OpCurProd;

    private Object opcurCob;
    private Object opcurCausa;
    private Object opcurCons;
    private Object opcurExp;
    private Object opcurCRVA;

    private Object opcurTipoRva;


    public GeneralesResponseDTO(Object OpCurCia,Object OpCurSecc,Object OpCurProd,Object opcurCob,Object opcurCausa,Object opcurCons, Object opcurExp, Object opcurCRVA){
        this.OpCurCia=OpCurCia;
        this.OpCurSecc =OpCurSecc;
        this.OpCurProd=OpCurProd;

        this.opcurCob=opcurCob;
        this.opcurCausa=opcurCausa;
        this.opcurCons=opcurCons;

        this.opcurExp=opcurExp;
        this.opcurCRVA=opcurCRVA;

    }
}
