package com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.procedures;

import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DTO.ReservaAutomaticaResponse;

public interface EditarRvaAutomaticaServiceInterface {
    ReservaAutomaticaResponse EditarRvaAutomatica(ReservaAutomaticaRequest request);
}
