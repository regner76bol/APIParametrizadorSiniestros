package com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.business;

import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DAO.CombinacionesRequestDAO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DTO.CombinacionCCCResponseDTO;

public interface CombinacionCCCBusinessInterface {
    CombinacionCCCResponseDTO CombinacionCCCBusiness(CombinacionesRequestDAO request);


}
