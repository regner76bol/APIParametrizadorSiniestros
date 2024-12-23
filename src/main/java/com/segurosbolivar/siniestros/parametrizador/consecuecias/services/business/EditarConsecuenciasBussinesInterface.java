package com.segurosbolivar.siniestros.parametrizador.consecuecias.services.business;

import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ConsecuenciasResponse;

public interface EditarConsecuenciasBussinesInterface {
    ConsecuenciasResponse EditarConsecuenciasBussinesService(ConsecuenciasRequest request);
}
