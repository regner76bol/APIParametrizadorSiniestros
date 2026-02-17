package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArchivosRequest extends RequestBase {

    public ArchivosRequest( String nombreArchivo,String rutaFisica,Integer tipo,String codAgrupacion,Integer idArchivo){
        this.nombreArchivo=nombreArchivo;
        this.rutaFisica=rutaFisica;
        this.tipo=tipo;
        this.codAgrupacion=codAgrupacion;
        this.idArchivo=idArchivo;

    }
    private String nombreArchivo;
    private String rutaFisica;
    private Integer tipo;
    private String codAgrupacion;
    private Integer idArchivo;

}
