package com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.procedures;

import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DTO.ExpedientesResponse;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DTO.ReservaAutomaticaResponse;

public interface ListarReservasServiceInterface {
    ReservaAutomaticaResponse ListarReservas(ReservaAutomaticaRequest request);
}
