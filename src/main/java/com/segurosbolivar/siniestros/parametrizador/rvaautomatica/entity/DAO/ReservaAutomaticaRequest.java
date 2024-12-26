package com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservaAutomaticaRequest extends RequestBase {
    private Integer idExprvaaut;
    private Integer codCob;
    private Integer codCausa;
    private Integer codConsecuencia;
    private String tipoExped;
    private Integer concepRva;
    private Integer tipoRva;

    private String datoVariable;
    private String liquidacionAutomatica;

    public ReservaAutomaticaRequest(Integer idExprvaaut,Integer codCob, Integer codCausa,Integer codConsecuencia,String tipoExped,Integer concepRva,Integer tipoRva,String datoVariable,String liquidacionAutomatica){
        this.idExprvaaut=idExprvaaut;
        this.codCob=codCob;
        this.codCausa=codCausa;
        this.codConsecuencia=codConsecuencia;
        this.tipoExped=tipoExped;
        this.concepRva=concepRva;
        this.tipoRva=tipoRva;
        this.datoVariable=datoVariable;
        this.liquidacionAutomatica=liquidacionAutomatica;
    }

}
