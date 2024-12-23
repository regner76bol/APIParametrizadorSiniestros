package com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure;

import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DAO.ExpedientesRequest;
import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DTO.ExpedientesResponse;

public interface EditarExpedientesProdServiceInterface {
    ExpedientesResponse EditarExpedProd(ExpedientesRequest request);
}
