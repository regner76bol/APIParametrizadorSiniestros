package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListarArchivosResponse extends ResponseBase {

    private Object Op_curArchivos;

    public ListarArchivosResponse(Object Op_curArchivos){

        this.Op_curArchivos=Op_curArchivos;
    }


}
