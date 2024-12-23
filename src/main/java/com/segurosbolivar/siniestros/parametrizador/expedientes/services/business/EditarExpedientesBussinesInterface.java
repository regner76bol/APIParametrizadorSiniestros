package com.segurosbolivar.siniestros.parametrizador.expedientes.services.business;

import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DAO.ExpedientesRequest;
import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DTO.ExpedientesResponse;

public interface EditarExpedientesBussinesInterface {
    ExpedientesResponse EditarExpedientes(ExpedientesRequest request);
}
