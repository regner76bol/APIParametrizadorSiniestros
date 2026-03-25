package com.segurosbolivar.siniestros.parametrizador.relexprecupero.controller;

import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DAO.RelExpRecuperoRequest;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DTO.RelExpRecuperoResponse;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.service.procedure.CrearRelExpRecaudoServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.service.procedure.EditarRelExpRecaudoServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.service.procedure.ListarRelExpRecaudosServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "*")
public class RelExpRecuperoController {

    @Autowired
    CrearRelExpRecaudoServiceInterface crerInterface;

    @Autowired
    EditarRelExpRecaudoServiceInterface editInterface;

    @Autowired
    ListarRelExpRecaudosServiceInterface listarInterface;

    @PostMapping("/parametrizador/relexprecupero/crear")
    public ResponseEntity<RelExpRecuperoResponse> CrearRelExpRecupero(@RequestBody RelExpRecuperoRequest request) {
        ResponseEntity<RelExpRecuperoResponse> response;
        RelExpRecuperoResponse rer = crerInterface.execute(request);
        if (rer.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
            response = new ResponseEntity<>(rer, HttpStatus.OK);
        } else if (rer.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
            response = new ResponseEntity<>(rer, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(rer, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/parametrizador/relexprecupero/editar")
    public ResponseEntity<RelExpRecuperoResponse> EditarRelExpRecupero(@RequestBody RelExpRecuperoRequest request) {
        ResponseEntity<RelExpRecuperoResponse> response;
        RelExpRecuperoResponse rer = editInterface.execute(request);
        if (rer.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
            response = new ResponseEntity<>(rer, HttpStatus.OK);
        } else if (rer.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
            response = new ResponseEntity<>(rer, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(rer, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/parametrizador/relexprecupero/listar")
    public ResponseEntity<RelExpRecuperoResponse> ListarRelExp(@RequestBody RelExpRecuperoRequest request){
        ResponseEntity<RelExpRecuperoResponse> response;
        RelExpRecuperoResponse rer = listarInterface.ListarRelExp(request);
        if (rer.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
            response = new ResponseEntity<>(rer, HttpStatus.OK);
        } else if (rer.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
            response = new ResponseEntity<>(rer, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(rer, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
