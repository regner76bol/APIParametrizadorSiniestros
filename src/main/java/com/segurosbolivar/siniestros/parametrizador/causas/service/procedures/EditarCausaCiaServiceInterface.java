package com.segurosbolivar.siniestros.parametrizador.causas.service.procedures;

import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.CausasResponse;

public interface EditarCausaCiaServiceInterface {
    CausasResponse EditarCausaPorCia(CausasRequest request);
}
