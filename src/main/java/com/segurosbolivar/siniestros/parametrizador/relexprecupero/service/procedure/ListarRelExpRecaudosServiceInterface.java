package com.segurosbolivar.siniestros.parametrizador.relexprecupero.service.procedure;

import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DAO.RelExpRecuperoRequest;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DTO.RelExpRecuperoResponse;

public interface ListarRelExpRecaudosServiceInterface {
    RelExpRecuperoResponse ListarRelExp(RelExpRecuperoRequest request);
}
