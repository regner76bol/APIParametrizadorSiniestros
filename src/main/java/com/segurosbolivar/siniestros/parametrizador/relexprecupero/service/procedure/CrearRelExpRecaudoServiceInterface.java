package com.segurosbolivar.siniestros.parametrizador.relexprecupero.service.procedure;

import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DAO.RelExpRecuperoRequest;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DTO.RelExpRecuperoResponse;

public interface CrearRelExpRecaudoServiceInterface {
    RelExpRecuperoResponse execute(RelExpRecuperoRequest request);
}
