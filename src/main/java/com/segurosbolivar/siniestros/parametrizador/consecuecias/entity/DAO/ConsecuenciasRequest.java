package com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ConsecuenciasResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsecuenciasRequest extends RequestBase {
    public ConsecuenciasRequest(Integer codCons, String consecuencia, Integer idConsecProd){
        this.codCons=codCons;
        this.consecuencia=consecuencia;
        this.idConsecProd=idConsecProd;

    }
    private Integer codCons;
    private String consecuencia;
    private Integer idConsecProd;
}
