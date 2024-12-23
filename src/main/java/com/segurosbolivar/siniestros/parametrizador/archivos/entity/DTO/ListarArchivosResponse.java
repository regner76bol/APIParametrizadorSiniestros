package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
public class ListarArchivosResponse {
    public ListarArchivosResponse(Object Op_curArchivos, BigDecimal Op_Resultado, String Op_MSG){
        /*this.codCia=codCia;
        this.codSecc=codSecc;
        this.codProd=codProd;
        this.compania=compania;
        this.seccion=seccion;
        this.producto=producto;
        this.ipParametrizacionMae=ipParametrizacionMae;
        this.nombreArchivo=nombreArchivo;
        this.rutaFisica=rutaFisica;
        this.fechaCreacion=fechaCreacion;
        this.usuarioCreacion=usuarioCreacion;
        this.estado=estado;
        this.tipo=tipo;
        this.idArchivo=idArchivo;
        this.cantRegs=cantRegs;*/
        this.Op_curArchivos=Op_curArchivos;
        this.Op_Resultado = Op_Resultado;
        this.Op_MSG = Op_MSG;
    }
    /*private Integer codCia;
    private Integer codSecc;
    private Integer codProd;
    private String compania;
    private String seccion;
    private String producto;
    private Integer ipParametrizacionMae;
    private String nombreArchivo;
    private String rutaFisica;
    private String fechaCreacion;
    private String usuarioCreacion;
    private Integer estado;
    private Integer tipo;
    private Integer idArchivo;
    private Integer cantRegs;*/
    private Object Op_curArchivos;
    private BigDecimal Op_Resultado;
    private String Op_MSG;
}
