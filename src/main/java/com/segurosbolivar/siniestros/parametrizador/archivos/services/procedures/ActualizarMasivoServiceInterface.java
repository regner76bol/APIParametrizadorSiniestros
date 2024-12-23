package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures;

import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.ActualizarMasivoRequest;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.ActualizarMasivoResponse;

public interface ActualizarMasivoServiceInterface {
    ActualizarMasivoResponse execute(ActualizarMasivoRequest request);
}
