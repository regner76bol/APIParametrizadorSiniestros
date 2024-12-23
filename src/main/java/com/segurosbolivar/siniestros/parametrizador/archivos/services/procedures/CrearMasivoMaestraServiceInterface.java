package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures;

import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.MasivoMaestraRequest;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.MasivoMeastraResponse;

public interface CrearMasivoMaestraServiceInterface {
    MasivoMeastraResponse execute(MasivoMaestraRequest request);
}
