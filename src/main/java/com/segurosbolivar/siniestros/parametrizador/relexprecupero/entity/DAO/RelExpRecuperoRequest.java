package com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DAO;


import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RelExpRecuperoRequest extends RequestBase {
    public String tipoExped;
    public String tipoExpRelac;
    public String mcaRelac;

    public RelExpRecuperoRequest(String tipoExped,String tipoExpRelac,String mcaRelac){
        this.tipoExped=tipoExped;
        this.tipoExpRelac=tipoExpRelac;
        this.mcaRelac=mcaRelac;
    }
}
