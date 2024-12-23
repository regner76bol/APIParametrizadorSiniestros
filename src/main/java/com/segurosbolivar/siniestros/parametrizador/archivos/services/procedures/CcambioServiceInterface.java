package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures;

import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.CcambioRequest;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.CcambioResponse;

public interface CcambioServiceInterface {
    CcambioResponse excecute(CcambioRequest request);
}
