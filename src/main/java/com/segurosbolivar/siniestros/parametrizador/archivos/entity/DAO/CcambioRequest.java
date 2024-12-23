package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CcambioRequest extends RequestBase {
    public CcambioRequest(Integer idParamMae, Integer tipo){

        this.idParamMae=idParamMae;
        this.tipo =tipo;

    }

    private Integer idParamMae;
    private Integer tipo;

}
