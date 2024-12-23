package com.segurosbolivar.siniestros.parametrizador.archivos.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.segurosbolivar.siniestros.commons.RequestBase;
import com.segurosbolivar.siniestros.commons.ResponseBase;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.*;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.*;
import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.segurosbolivar.siniestros.config.Utilities.*;


import java.math.BigDecimal;

@RestController
//@RequestMapping(value = "archivos")
@CrossOrigin(origins = "*")
public class ArchivosController {

    @Autowired
    private ListarArchivosServiceInterface archivosInterface;

    @Autowired
    private CcambioServiceInterface ccambioInterface;

    @Autowired
    private  ListarLogServiceInterface logInterface;
@Autowired
    private CrearArchivosServiceInterface interfaz;

@Autowired
private CrearMasivoMaestraServiceInterface masivoInterface;

@Autowired
private ActualizarMasivoServiceInterface ActMasivoInterface;

@Autowired
private ParametrizarSiniestrosServiceInterface IParametrizar;

@GetMapping("/parametrizador/archivos/lista")
public ResponseEntity<ListarArchivosResponse> getListarArchivos(@RequestBody RequestBase request) throws Exception {

    ResponseEntity<ListarArchivosResponse> response = null;

    try {
        ListarArchivosResponse archivos = archivosInterface.execute();
        if (archivos.getOp_Resultado() == BigDecimal.valueOf(0)) {
            response = new ResponseEntity<ListarArchivosResponse>(archivos, HttpStatus.OK);
        } else if (archivos.getOp_Resultado() == BigDecimal.valueOf(-1)) {
            response = new ResponseEntity<ListarArchivosResponse>(archivos, HttpStatus.OK);
        } else {
            response = new ResponseEntity<ListarArchivosResponse>(archivos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } catch (Exception e) {
        ListarArchivosResponse lista = new ListarArchivosResponse();
        lista.setOp_Resultado(BigDecimal.valueOf(-1));
        lista.setOp_MSG(e.getCause().getMessage());
    }

    return response;
}

@GetMapping(value = "/parametrizador/archivos/ccambio")
public ResponseEntity<CcambioResponse> ListarCCambio(@RequestBody CcambioRequest request) throws JsonProcessingException {
 ResponseEntity<CcambioResponse> response = null;
 CcambioResponse ccambio = ccambioInterface.excecute(request);
    if (ccambio.getOp_Resultado()== BigDecimal.valueOf(0)){
        response=new ResponseEntity<CcambioResponse>(ccambio,HttpStatus.OK);
    }
    else if (ccambio.getOp_Resultado()==BigDecimal.valueOf(-1)){
        response=new ResponseEntity<CcambioResponse>(ccambio,HttpStatus.OK);
    }
    else {
        response=new ResponseEntity<CcambioResponse>(ccambio,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
}

@GetMapping("/parametrizador/archivos/log")
public ResponseEntity<ListarLogResponse> ListarLog(@RequestBody ListarLogRequest request) throws JsonProcessingException{
    ResponseEntity<ListarLogResponse> response = null;
    ListarLogResponse log = logInterface.execute(request);
    try {
        if (log.getOp_Resultado()==BigDecimal.valueOf(0)) {
            response = new ResponseEntity<ListarLogResponse>(log,HttpStatus.OK);
        } else if (log.getOp_Resultado() == BigDecimal.valueOf(-1)) {
            response = new ResponseEntity<ListarLogResponse>(log, HttpStatus.OK);
        } else {
            response = new ResponseEntity<ListarLogResponse>(log, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    } catch (Exception e) {
        ListarLogResponse res= new ListarLogResponse();
        res.setOp_Resultado(BigDecimal.valueOf(-1));
        res.setOp_MSG(e.getCause().getMessage());
        response = new ResponseEntity<ListarLogResponse>(res,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
}


@PostMapping(value = "/parametrizador/archivo/creararchivo")
    public ResponseEntity<ArchivosResponse> CrearArchivo(@RequestBody ArchivosRequest request){
        ResponseEntity<ArchivosResponse> response = null;
        ArchivosResponse archivo = interfaz.execute(request);

        if (archivo.getOp_Resultado()==0){
            response = new ResponseEntity<ArchivosResponse>(archivo, HttpStatus.OK);
        }
        else if(archivo.getOp_Resultado()==-1){
            response = new ResponseEntity<ArchivosResponse>(archivo, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<ArchivosResponse>(archivo, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    return response;
}

@PostMapping("/parametrizador/masivo/maestra")
    public ResponseEntity<MasivoMeastraResponse> CrearMaestra(@RequestBody MasivoMaestraRequest request){
    ResponseEntity<MasivoMeastraResponse> response= null;
    MasivoMeastraResponse masivo = masivoInterface.execute(request);
    if (masivo.getOp_Resultado()==0)
    {
        response = new ResponseEntity<MasivoMeastraResponse>(masivo,HttpStatus.OK);
    }
    else if (masivo.getOp_Resultado()==-1)
    {
        response = new ResponseEntity<MasivoMeastraResponse>(masivo,HttpStatus.OK);
    }
    else {
        response = new ResponseEntity<MasivoMeastraResponse>(masivo,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
}

@PostMapping("/parametrizador/masivo/actualizar")
    public ResponseEntity<ActualizarMasivoResponse> ActualizarMasivo(@RequestBody ActualizarMasivoRequest request){
    ResponseEntity<ActualizarMasivoResponse> response= null;
    ActualizarMasivoResponse ActMasivo = ActMasivoInterface.execute(request);
    if (ActMasivo.getOp_Resultado()==0){
        response = new ResponseEntity<ActualizarMasivoResponse>(ActMasivo,HttpStatus.OK);
    }
    else if (ActMasivo.getOp_Resultado()==-1){
        response = new ResponseEntity<ActualizarMasivoResponse>(ActMasivo,HttpStatus.OK);
    }
    else {
        response = new ResponseEntity<ActualizarMasivoResponse>(ActMasivo,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
}

@PostMapping("/parametrizador/masivo/parametrizar")
    public ResponseEntity<ResponseBase> Parametrizar(@RequestBody RequestBase request) throws JsonProcessingException{
    ResponseEntity<ResponseBase> response = null;
    ResponseBase par = IParametrizar.execute(request);
    if (par.getOp_Resultado()==BigDecimal.valueOf(0)){
        response = new ResponseEntity<ResponseBase>(par,HttpStatus.OK);
    }
    else if (par.getOp_Resultado()==BigDecimal.valueOf(-1)){
        response = new ResponseEntity<ResponseBase>(par,HttpStatus.OK);
    }
    else {
        response = new ResponseEntity<ResponseBase>(par,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
}

}
