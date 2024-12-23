package com.segurosbolivar.siniestros.generales.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.segurosbolivar.siniestros.commons.RequestBase;
import com.segurosbolivar.siniestros.generales.entity.DTO.GeneralesResponseDTO;
import com.segurosbolivar.siniestros.generales.services.procedures.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;

@RestController
@CrossOrigin(origins = "*")
public class GeneralesController {

    @Autowired
    CompañiaServiceInterface ICompania;
    @Autowired
    SeccionServiceInterface ISeccion;
    @Autowired
    ProductoServiceInterface IProducto;

    @Autowired
    CargarCoberturasServiceInterface ICoberturas;

    @Autowired
    CargarCausasServiceInterface ICausas;

    @Autowired
    CargarConsecuenciasServiceInterface IConsecuencias;

    @Autowired
    CargarExpedientesServiceInterface IExpedientes;

    @Autowired
    CargarConceptosRVAServiceInterface IConcepRva;

    @Autowired
    CargarTipoReservaServiceInterface ITipoRva;

    @GetMapping("/generales/cia")
    public ResponseEntity<GeneralesResponseDTO> ListarCia() throws JsonProcessingException {
        ResponseEntity<GeneralesResponseDTO> response ;
        GeneralesResponseDTO cia ;
        try {
            cia= ICompania.ListarCompañia();
            if (cia.getOp_Resultado()== BigDecimal.valueOf(0)) {
                response = new ResponseEntity<>(cia, HttpStatus.OK);

            } else if (cia.getOp_Resultado() == BigDecimal.valueOf(-1)) {
                response = new ResponseEntity<>(cia, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(cia, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (Exception e){
            GeneralesResponseDTO res = new GeneralesResponseDTO();
            res.setOp_Resultado(BigDecimal.valueOf(-1));
            res.setOp_MSG(e.getCause().getMessage());
            response = new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


    @PostMapping("/generales/secc")
    public ResponseEntity<GeneralesResponseDTO> ListarSecc(@RequestBody RequestBase request) throws JsonProcessingException {
        ResponseEntity<GeneralesResponseDTO> response ;
        GeneralesResponseDTO secc;
        try {
            secc = ISeccion.ListarSeccion(request);
            if (secc.getOp_Resultado()== BigDecimal.valueOf(0)) {
                response = new ResponseEntity<>(secc, HttpStatus.OK);

            } else if (secc.getOp_Resultado() == BigDecimal.valueOf(-1)) {
                response = new ResponseEntity<>(secc, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(secc, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (Exception e){
            GeneralesResponseDTO res = new GeneralesResponseDTO();
            res.setOp_Resultado(BigDecimal.valueOf(-1));
            res.setOp_MSG(e.getCause().getMessage());
            response = new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/generales/prod")
    public ResponseEntity<GeneralesResponseDTO> ListarProd(@RequestBody RequestBase request) throws JsonProcessingException {
        ResponseEntity<GeneralesResponseDTO> response ;
        GeneralesResponseDTO prod;
        try {
            prod = IProducto.ListarProducto(request);
            if (prod.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
                response = new ResponseEntity<>(prod, HttpStatus.OK);

            } else if (prod.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
                response = new ResponseEntity<>(prod, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(prod, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (Exception e){
            GeneralesResponseDTO res = new GeneralesResponseDTO();
            res.setOp_Resultado(BigDecimal.valueOf(-1));
            res.setOp_MSG(e.getCause().getMessage());
            response = new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/generales/coberturas")
    public ResponseEntity<GeneralesResponseDTO> CargarCoberturas(@RequestBody RequestBase request)throws JsonProcessingException{
        ResponseEntity<GeneralesResponseDTO> response;
        GeneralesResponseDTO cob;

            cob =  ICoberturas.CargarCobertura(request);
            if (cob.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
                response = new ResponseEntity<>(cob, HttpStatus.OK);

            } else if (cob.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
                response = new ResponseEntity<>(cob, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(cob, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return response;
    }

    @PostMapping("/generales/causas")
    public ResponseEntity<GeneralesResponseDTO> CargarCausas(@RequestBody RequestBase request)throws JsonProcessingException{
        ResponseEntity<GeneralesResponseDTO> response;
        GeneralesResponseDTO cau=  ICausas.CargarCausas(request);
        if (cau.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
            response = new ResponseEntity<>(cau, HttpStatus.OK);

        } else if (cau.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
            response = new ResponseEntity<>(cau, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(cau, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/generales/consecuencias")
    public ResponseEntity<GeneralesResponseDTO> CargarConsecuencias(@RequestBody RequestBase request)throws JsonProcessingException{
        ResponseEntity<GeneralesResponseDTO> response;
        GeneralesResponseDTO con=  IConsecuencias.CargarConsecuencias(request);
        if (con.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
            response = new ResponseEntity<>(con, HttpStatus.OK);

        } else if (con.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
            response = new ResponseEntity<>(con, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(con, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/generales/expedientes")
    public ResponseEntity<GeneralesResponseDTO> CargarExpedientes(@RequestBody RequestBase request)throws JsonProcessingException{
        ResponseEntity<GeneralesResponseDTO> response;
        GeneralesResponseDTO exp=  IExpedientes.CargarExpedientess(request);
        if (exp.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
            response = new ResponseEntity<>(exp, HttpStatus.OK);

        } else if (exp.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
            response = new ResponseEntity<>(exp, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(exp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/generales/conceprva")
    public ResponseEntity<GeneralesResponseDTO> CargarConceptosRVA(@RequestBody RequestBase request)throws JsonProcessingException{
        ResponseEntity<GeneralesResponseDTO> response;
        GeneralesResponseDTO rva=  IConcepRva.CargarConceptosRVA(request);
        if (rva.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
            response = new ResponseEntity<>(rva, HttpStatus.OK);

        } else if (rva.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
            response = new ResponseEntity<>(rva, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(rva, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/generales/tiporva")
    public ResponseEntity<GeneralesResponseDTO> CargarTipoRVA()throws JsonProcessingException{
        ResponseEntity<GeneralesResponseDTO> response;
        GeneralesResponseDTO rva=  ITipoRva.CargarTipoReserva();
        if (rva.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
            response = new ResponseEntity<>(rva, HttpStatus.OK);

        } else if (rva.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
            response = new ResponseEntity<>(rva, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(rva, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
