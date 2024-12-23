package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures;

import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.ListarLogRequest;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.ListarLogResponse;

public interface ListarLogServiceInterface {
    ListarLogResponse execute(ListarLogRequest request);
}
