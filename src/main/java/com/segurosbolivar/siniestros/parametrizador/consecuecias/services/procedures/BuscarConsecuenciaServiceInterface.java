package com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures;

import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ConsecuenciasResponse;

public interface BuscarConsecuenciaServiceInterface {
    ConsecuenciasResponse Execute(ConsecuenciasRequest request);
}