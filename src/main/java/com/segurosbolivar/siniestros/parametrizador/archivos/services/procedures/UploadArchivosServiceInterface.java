package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures;

import org.springframework.web.multipart.MultipartFile;

public interface UploadArchivosServiceInterface {
    String store(MultipartFile file);
}

