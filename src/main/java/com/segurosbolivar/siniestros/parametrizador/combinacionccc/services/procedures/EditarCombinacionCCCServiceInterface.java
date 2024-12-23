package com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.procedures;

import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DAO.CombinacionesRequestDAO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DTO.CombinacionCCCResponseDTO;

public interface EditarCombinacionCCCServiceInterface {
    CombinacionCCCResponseDTO EditarCombCCC(CombinacionesRequestDAO request);
}
