package com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.procedures;

import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DAO.CombinacionExpCRvaRequest;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DTO.CombinacionExpCRvaResponse;

public interface CombinacionExpCrvaServiceInterface {
    CombinacionExpCRvaResponse execute(CombinacionExpCRvaRequest request);
}
