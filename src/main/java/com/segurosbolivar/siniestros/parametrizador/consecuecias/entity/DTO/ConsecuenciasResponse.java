package com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsecuenciasResponse extends ResponseBase {

    public ConsecuenciasResponse(Integer Op_ConsxCia, Integer Op_ConsxSecc, Integer Op_ConsxProd){
        this.Op_ConsxCia=Op_ConsxCia;
        this.Op_ConsxSecc=Op_ConsxSecc;
        this.Op_ConsxProd=Op_ConsxProd;
    }

    private Integer Op_ConsxCia;
    private Integer Op_ConsxSecc;
    private Integer Op_ConsxProd;
}
