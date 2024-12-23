package com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CombinacionesRequestDAO extends RequestBase {

    private BigDecimal codCob ;
    private BigDecimal codCausa;
    private BigDecimal codConsecuencia;
    private BigDecimal idA7000100;

    public CombinacionesRequestDAO(BigDecimal codCob, BigDecimal codCausa,BigDecimal codConsecuencia, BigDecimal idA7000100){
        this.codCob=codCob;
        this.codCausa=codCausa;
        this.codConsecuencia=codConsecuencia;
        this.idA7000100=idA7000100;
    }



}
