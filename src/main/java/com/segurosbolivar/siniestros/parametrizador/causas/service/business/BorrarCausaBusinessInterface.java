package com.segurosbolivar.siniestros.parametrizador.causas.service.business;

import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.CausasResponse;

public interface BorrarCausaBusinessInterface {
    public CausasResponse BorrarCausa(CausasRequest request);
}
