package com.segurosbolivar.siniestros.commons;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class RequestBase {

    public RequestBase(String codCia, String codSecc,String codProd,String codUsr, String idParamMae, String simulacion){
        this.codCia=codCia;
        this.codSecc=codSecc;
        this.codProd=codProd;
        this.codUsr=codUsr;
        this.idParamMae=idParamMae;
        this.simulacion=simulacion;
    }
    private String codCia;
    private String codSecc;
    private String codProd;
    private String codUsr;
    private String idParamMae;
    private String simulacion;
}

