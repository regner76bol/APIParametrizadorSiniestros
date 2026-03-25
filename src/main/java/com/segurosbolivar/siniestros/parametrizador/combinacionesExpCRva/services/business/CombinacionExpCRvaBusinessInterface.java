package com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.business;

import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DAO.CombinacionExpCRvaRequest;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DTO.CombinacionExpCRvaResponse;

public interface CombinacionExpCRvaBusinessInterface {
    CombinacionExpCRvaResponse CombinacionExpCRva(CombinacionExpCRvaRequest request);
}
