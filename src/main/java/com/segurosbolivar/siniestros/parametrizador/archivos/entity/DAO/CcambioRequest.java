package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CcambioRequest extends RequestBase {
    public CcambioRequest(String idParamMae, Integer tipo){
        this.idParamMae=idParamMae;
        this.tipo =tipo;
    }

    private String idParamMae;
    private Integer tipo;

}
