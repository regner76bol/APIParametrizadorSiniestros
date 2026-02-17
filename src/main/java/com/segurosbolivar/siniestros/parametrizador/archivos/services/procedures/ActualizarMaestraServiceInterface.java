package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures;

import com.segurosbolivar.siniestros.commons.ResponseBase;

import java.math.BigDecimal;

public interface ActualizarMaestraServiceInterface {
    ResponseBase ActualizarMaestra(BigDecimal idSecMae);
}
