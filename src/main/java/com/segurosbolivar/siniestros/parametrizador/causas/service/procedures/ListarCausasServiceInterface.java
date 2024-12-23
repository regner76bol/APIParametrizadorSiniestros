package com.segurosbolivar.siniestros.parametrizador.causas.service.procedures;

import com.segurosbolivar.siniestros.commons.RequestBase;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.ListarCausasResponse;

public interface ListarCausasServiceInterface {
    ListarCausasResponse ListarCausas(CausasRequest request);
}
