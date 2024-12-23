package com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.business;

import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DAO.CombinacionExpCRvaRequest;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DTO.CombinacionExpCRvaResponse;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;

public interface CombinacionExpCRvaBusinessInterface {
    CombinacionExpCRvaResponse CombinacionExpCRva(CombinacionExpCRvaRequest request);
}
