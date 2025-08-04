package com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RelExpRecuperoResponse extends ResponseBase {

    public Object op_curRel;

    public RelExpRecuperoResponse(Object op_curRel){
        this.op_curRel = op_curRel;
    }
}
