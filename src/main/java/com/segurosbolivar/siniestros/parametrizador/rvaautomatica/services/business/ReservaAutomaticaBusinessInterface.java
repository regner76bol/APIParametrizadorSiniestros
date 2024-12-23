package com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.business;

import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DTO.ReservaAutomaticaResponse;

public interface ReservaAutomaticaBusinessInterface {
    ReservaAutomaticaResponse ReservaBusiness(ReservaAutomaticaRequest request);
}
