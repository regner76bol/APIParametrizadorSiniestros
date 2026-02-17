package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MasivoMeastraResponse extends ResponseBase {

    public MasivoMeastraResponse(Integer Op_SecMae) {
        this.Op_SecMae=Op_SecMae;
    }

    private Integer Op_SecMae;
}
