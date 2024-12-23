package com.segurosbolivar.siniestros.parametrizador.consecuecias.services.business;

import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ConsecuenciasResponse;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ListarConsecuenciasResponse;

public interface ConsecuenciasBusinessInterface {
    ListarConsecuenciasResponse ListarConsecuencias(ConsecuenciasRequest request);
    ConsecuenciasResponse CrearConsecuencia(ConsecuenciasRequest request);
}
