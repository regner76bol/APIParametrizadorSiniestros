package com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.procedures;

import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DAO.CombinacionesRequestDAO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DTO.CombinacionCCCResponseDTO;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;

public interface CombinacionCCCServiceInterface {
    CombinacionCCCResponseDTO execute(CombinacionesRequestDAO request);
}
