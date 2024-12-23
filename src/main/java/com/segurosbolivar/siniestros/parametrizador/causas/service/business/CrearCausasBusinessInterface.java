package com.segurosbolivar.siniestros.parametrizador.causas.service.business;

import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.CausasResponse;

public interface CrearCausasBusinessInterface {
    CausasResponse CrearCausa(CausasRequest request);
    Integer BuscarCodigoCausa(CausasRequest request);
}
