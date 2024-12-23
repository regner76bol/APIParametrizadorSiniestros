package com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.business.impl;

import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DAO.CombinacionesRequestDAO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DTO.CombinacionCCCResponseDTO;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;

public interface CombinacionCCCBusinessInterface {
    CombinacionCCCResponseDTO CombinacionCCCBusiness(CombinacionesRequestDAO request);


}
