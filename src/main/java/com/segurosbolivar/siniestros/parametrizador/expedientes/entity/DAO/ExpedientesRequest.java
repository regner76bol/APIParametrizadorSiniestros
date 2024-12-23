package com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpedientesRequest extends RequestBase {

    public ExpedientesRequest(String tipoExped, String descExped, String claseExped){
        this.tipoExped=tipoExped;
        this.descExped=descExped;
        this.claseExped=claseExped;
    }

    private String tipoExped;
    private String descExped;
    private String claseExped;
}
