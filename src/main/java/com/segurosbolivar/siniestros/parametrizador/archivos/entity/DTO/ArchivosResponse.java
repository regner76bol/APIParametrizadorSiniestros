package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class    ArchivosResponse extends ResponseBase {

    public ArchivosResponse(BigDecimal idArchivo,BigDecimal secMae)  {
       this.idArchivo=idArchivo;
        this.secMae=secMae;

    }

    private BigDecimal idArchivo;
    private BigDecimal secMae;

}
