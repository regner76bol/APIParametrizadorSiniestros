package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class ActualizarMasivoRequest {

    public ActualizarMasivoRequest(Integer SecMae){

        this.SecMae= SecMae;

    }
    private Integer SecMae;

}
