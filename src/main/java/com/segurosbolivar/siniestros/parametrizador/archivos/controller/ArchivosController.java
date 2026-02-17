package com.segurosbolivar.siniestros.parametrizador.archivos.controller;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.*;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.*;
import com.segurosbolivar.siniestros.parametrizador.archivos.services.bussines.ArchivosBussinesInterface;
import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.segurosbolivar.siniestros.funciones.funcionesInterface;

import java.math.BigDecimal;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin(origins = "*")
public class ArchivosController {

    @Value("${file.upload.dir}")
    private final Path filePath = Paths.get("${file.upload.dir}");

    @Autowired
    private ListarArchivosServiceInterface archivosInterface;

    @Autowired
    private CcambioServiceInterface ccambioInterface;

    @Autowired
    private  ListarLogServiceInterface logInterface;

    @Autowired
    private ArchivosBussinesInterface IArchivosBussines;


@GetMapping("/parametrizador/archivos/lista")
public ResponseEntity<ListarArchivosResponse> getListarArchivos()  {

    ResponseEntity<ListarArchivosResponse> response = null;

    try {
        ListarArchivosResponse archivos = archivosInterface.execute();
        if (archivos.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
            response = new ResponseEntity<>(archivos, HttpStatus.OK);
        } else if (archivos.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
            response = new ResponseEntity<>(archivos, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(archivos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } catch (Exception e) {
        ListarArchivosResponse lista = new ListarArchivosResponse();
        lista.setOp_Resultado(BigDecimal.valueOf(-1));
        lista.setOp_MSG(e.getCause().getMessage());
    }

    return response;
}

@PostMapping(value = "/parametrizador/archivos/ccambio")
public ResponseEntity<CcambioResponse> ListarCCambio(@RequestBody CcambioRequest request) {
 ResponseEntity<CcambioResponse> response;
 CcambioResponse ccambio = ccambioInterface.excecute(request);
    if (ccambio.getOp_Resultado().equals(BigDecimal.valueOf(0))){
        response=new ResponseEntity<>(ccambio,HttpStatus.OK);
    }
    else if (ccambio.getOp_Resultado().equals(BigDecimal.valueOf(-1))){
        response=new ResponseEntity<>(ccambio,HttpStatus.OK);
    }
    else {
        response=new ResponseEntity<>(ccambio,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
}

@PostMapping("/parametrizador/archivos/log")
public ResponseEntity<ListarLogResponse> ListarLog(@RequestBody ListarLogRequest request) {
    ResponseEntity<ListarLogResponse> response;
    ListarLogResponse log = logInterface.execute(request);
    try {
        if (log.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
            response = new ResponseEntity<>(log,HttpStatus.OK);
        } else if (log.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
            response = new ResponseEntity<>(log, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(log, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    } catch (Exception e) {
        ListarLogResponse res= new ListarLogResponse();
        res.setOp_Resultado(BigDecimal.valueOf(-1));
        res.setOp_MSG(e.getCause().getMessage());
        response = new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
}

    @PostMapping(value = "/parametrizador/archivos/cargar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseBase> CargarArchivo(@RequestParam(value = "file", required = false) MultipartFile file,
                                                      @RequestBody ArchivosRequest request) {
    ResponseBase res = new ResponseBase();
    ResponseEntity<ResponseBase> response;
    System.out.println("hola");
    try {
         //Validar que el archivo no sea nulo
        if (file == null) {
            res.setOp_MSG("Error: No se recibió ningún archivo. Verifique que el parámetro se llame 'file'");
            res.setOp_Resultado(BigDecimal.valueOf(-1));
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
         // Validar que el archivo no esté vacío
        if (file.isEmpty()) {
            res.setOp_MSG("Error: El archivo está vacío");
            res.setOp_Resultado(BigDecimal.valueOf(-1));
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        
        // Log para debugging
        System.out.println("Archivo recibido: " + file.getOriginalFilename());
        System.out.println("Tamaño: " + file.getSize() + " bytes");
        System.out.println("Content-Type: " + file.getContentType());

        res = IArchivosBussines.CargarArchivo(filePath,file,request);

        response = new ResponseEntity<>(res, HttpStatus.OK);

    }
    catch (Exception e){
        //e.printStackTrace(); // Para ver el error completo en consola
        res.setOp_MSG("Error: "+ e.getMessage() );
        res.setOp_Resultado(BigDecimal.valueOf(-1));
        response = new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    return response;
  }
}
