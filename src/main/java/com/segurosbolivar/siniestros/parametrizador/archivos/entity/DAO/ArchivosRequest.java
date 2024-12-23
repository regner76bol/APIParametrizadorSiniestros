package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArchivosRequest extends RequestBase {

    public ArchivosRequest( String nombreArchivo,String rutaFisica,Integer tipo){
        this.nombreArchivo=nombreArchivo;
        this.rutaFisica=rutaFisica;
        this.tipo=tipo;
    }
    private String nombreArchivo;
    private String rutaFisica;
    private Integer tipo;

}
