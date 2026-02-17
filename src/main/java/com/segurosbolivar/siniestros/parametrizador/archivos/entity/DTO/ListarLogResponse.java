package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ListarLogResponse extends ResponseBase {
    public ListarLogResponse(Object Op_curLog){
        this.Op_curLog=Op_curLog;

    }
    private Object Op_curLog;

}
