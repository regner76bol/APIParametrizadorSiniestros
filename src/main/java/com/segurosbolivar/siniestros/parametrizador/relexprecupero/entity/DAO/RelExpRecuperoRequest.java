package com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DAO;


import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RelExpRecuperoRequest extends RequestBase {
    public String tipoExped;
    public String tipoExpRelac;
    public String mcaRelac;
    public String tipoExpedOld;
    public String tipoExpRelacOld;

    public RelExpRecuperoRequest(String tipoExped,String tipoExpRelac,String mcaRelac,String tipoExpedOld,String tipoExpRelacOld){
        this.tipoExped=tipoExped;
        this.tipoExpRelac=tipoExpRelac;
        this.mcaRelac=mcaRelac;
        this.tipoExpedOld=tipoExpedOld;
        this.tipoExpRelacOld=tipoExpRelacOld;
    }
}
