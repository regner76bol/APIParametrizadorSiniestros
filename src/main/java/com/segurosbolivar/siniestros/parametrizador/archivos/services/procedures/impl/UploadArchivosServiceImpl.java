package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.UploadArchivosServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class UploadArchivosServiceImpl implements UploadArchivosServiceInterface {

    @Autowired
    DataSource dataSource;

    //@Value("${file.upload.dir}")
    private String uploadDir= "@Value(${file.upload.dir})";

    private final Path rootLocation;

    public UploadArchivosServiceImpl() {
        //String uploadDir = "C:\\Users\\79745463\\Documents\\Archivos\\";
        this.rootLocation = Paths.get(this.uploadDir);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (file.isEmpty()) throw new RuntimeException("Archivo vacío");
            Path destinationFile = this.rootLocation.resolve(filename).normalize();
            try (InputStream in = file.getInputStream())
            {
                Files.copy(in, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return destinationFile.toString();
        }
        catch (IOException e) {
            throw new RuntimeException("Fallo al guardar archivo", e);
        }
    }


}
