package com.segurosbolivar.siniestros.parametrizador.archivos.services.bussines;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.ArchivosRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface ArchivosBussinesInterface {
    ResponseBase CargarArchivo(Path filePath, MultipartFile file, ArchivosRequest request) throws IOException;
}
