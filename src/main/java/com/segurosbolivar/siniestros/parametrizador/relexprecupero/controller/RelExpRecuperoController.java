package com.segurosbolivar.siniestros.parametrizador.relexprecupero.controller;


import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.CausasResponse;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DAO.RelExpRecuperoRequest;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DTO.RelExpRecuperoResponse;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.service.procedure.CrearRelExpRecaudoServiceInterface;
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

    @PostMapping("/parametrizador/relexprecupero/crear")
    public ResponseEntity<RelExpRecuperoResponse> CrearRelExpRecupero(@RequestBody RelExpRecuperoRequest request){
        ResponseEntity<RelExpRecuperoResponse> response = null;
        RelExpRecuperoResponse rer = crerInterface.execute(request);
        if (rer.getOp_Resultado()== BigDecimal.valueOf(0)) {
            response= new ResponseEntity<RelExpRecuperoResponse>(rer, HttpStatus.OK);
        } else if (rer.getOp_Resultado()== BigDecimal.valueOf(-1)) {
            response= new ResponseEntity<RelExpRecuperoResponse>(rer, HttpStatus.OK);
        }
        else {
            response= new ResponseEntity<RelExpRecuperoResponse>(rer, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return response;

    }

}
