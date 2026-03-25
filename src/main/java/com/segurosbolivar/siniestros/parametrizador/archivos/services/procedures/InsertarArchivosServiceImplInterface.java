package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures;

import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.ArchivosRequest;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.ArchivosResponse;

public interface InsertarArchivosServiceImplInterface {
     ArchivosResponse InsertarArchivo(ArchivosRequest request);
}
