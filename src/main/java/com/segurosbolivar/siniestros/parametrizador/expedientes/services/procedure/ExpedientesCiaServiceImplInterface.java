package com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure;

import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DAO.ExpedientesRequest;
import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DTO.ExpedientesResponse;

public interface ExpedientesCiaServiceImplInterface {
    ExpedientesResponse execute(ExpedientesRequest request);
}
