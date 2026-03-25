package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO;

import com.segurosbolivar.siniestros.commons.RequestBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ArchivosRequest extends RequestBase {

    public ArchivosRequest( String nombreArchivo,String rutaFisica,String tipo,String codAgrupacion,BigDecimal idArchivo){
        this.nombreArchivo=nombreArchivo;
        this.rutaFisica=rutaFisica;
        this.tipo= tipo;
        this.codAgrupacion=codAgrupacion;
        this.idArchivo=idArchivo;

    }
    private String nombreArchivo;
    private String rutaFisica;
    private String tipo;
    private String codAgrupacion;
    private BigDecimal idArchivo;

}
