package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;


@Data
public class ListarArchivosRequest extends RequestBase {

    public ListarArchivosRequest(){
        this.setCodCia(getCodCia());
        this.setCodSecc(getCodSecc());
        this.setCodProd(getCodProd());
        this.setCodUsr(getCodUsr());
    }

}
