package com.segurosbolivar.siniestros.commons;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class RequestBase {

    public RequestBase(Integer codCia, Integer codSecc,Integer codProd,String codUsr, Integer idParamMae, Integer simulacion){
        this.codCia=codCia;
        this.codSecc=codSecc;
        this.codProd=codProd;
        this.codUsr=codUsr;
        this.idParamMae=idParamMae;
        this.simulacion=simulacion;
    }
    private Integer codCia;
    private Integer codSecc;
    private Integer codProd;
    private String codUsr;
    private Integer idParamMae;
    private Integer simulacion;
}

