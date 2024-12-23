package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListarLogRequest extends RequestBase {
    public ListarLogRequest(Integer idParamMae){
        this.idParamMae=idParamMae;
    }
    private Integer idParamMae;
}
