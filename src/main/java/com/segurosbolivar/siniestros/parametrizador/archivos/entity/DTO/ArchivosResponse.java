package com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class    ArchivosResponse extends ResponseBase {

    public ArchivosResponse(BigDecimal secMae)  {
       this.secMae=secMae;
    }

    private BigDecimal secMae;

}
