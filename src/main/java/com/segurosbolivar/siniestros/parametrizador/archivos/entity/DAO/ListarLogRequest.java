package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ListarLogRequest extends RequestBase {
    public ListarLogRequest(Integer idParamMae){

        this.idParamMae=idParamMae;
    }
    private Integer idParamMae;
}
