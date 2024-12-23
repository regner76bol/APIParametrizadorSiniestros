package com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures;

import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ListarConsecuenciasResponse;

public interface ListarConsecuenciasServiceInterface {
     ListarConsecuenciasResponse ListarConsecuencias(ConsecuenciasRequest request);
}
