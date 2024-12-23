package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
public class ArchivosResponse {

    public ArchivosResponse(Integer op_Resultado, String Op_MSG) {
        this.Op_Resultado = op_Resultado;
        this.Op_MSG = Op_MSG;
    }

    private Integer Op_Resultado;
    private String Op_MSG;
}
