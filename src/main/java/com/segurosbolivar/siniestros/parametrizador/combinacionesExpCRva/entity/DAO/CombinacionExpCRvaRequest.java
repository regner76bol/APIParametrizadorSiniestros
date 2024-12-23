package com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CombinacionExpCRvaRequest extends RequestBase {
    private String tipoExped;
    private Integer codConcepRva;
    private Integer codCob;
    private Integer codCausa;
    private Integer idA7000100;

    private CombinacionExpCRvaRequest(String tipoExped,Integer codConcepRva,Integer codCob,Integer codCausa, Integer idA7000100){
        this.tipoExped=tipoExped;
        this.codConcepRva=codConcepRva;
        this.codCob=codCob;
        this.codCausa=codCausa;
        this.idA7000100=idA7000100;
    }
}
