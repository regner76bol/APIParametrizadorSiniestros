package com.segurosbolivar.siniestros.generales.services.procedures;

import com.segurosbolivar.siniestros.commons.RequestBase;
import com.segurosbolivar.siniestros.generales.entity.DTO.GeneralesResponseDTO;


public interface CargarConsecuenciasServiceInterface {
    GeneralesResponseDTO CargarConsecuencias(RequestBase request);
}
