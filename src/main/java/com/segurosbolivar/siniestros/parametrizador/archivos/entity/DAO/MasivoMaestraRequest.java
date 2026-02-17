package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MasivoMaestraRequest extends RequestBase {

    public MasivoMaestraRequest(String codAgrupacion, Integer idArchivo){
        this.codAgrupacion=codAgrupacion;
        this.idArchivo=idArchivo;

    }
    private String codAgrupacion;
    private Integer idArchivo;

}
