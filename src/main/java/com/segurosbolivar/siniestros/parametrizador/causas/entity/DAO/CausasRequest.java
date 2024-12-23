package com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@NoArgsConstructor
public class CausasRequest extends RequestBase {
    public CausasRequest(Integer codCausa,Integer tipoCausa,Integer tipoCausaAnt,Integer idCausaProd, String causa){
        this.codCausa=codCausa;
        this.tipoCausa=tipoCausa;
        this.tipoCausaAnt=tipoCausaAnt;
        this.idCausaProd=idCausaProd;
        this.causa=causa;
    }
    private Integer codCausa;
    private Integer tipoCausa;
    private Integer tipoCausaAnt;
    private Integer idCausaProd;
    private String causa;
}
