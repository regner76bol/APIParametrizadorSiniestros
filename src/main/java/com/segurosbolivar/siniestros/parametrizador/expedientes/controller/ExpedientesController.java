package com.segurosbolivar.siniestros.parametrizador.expedientes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DAO.ExpedientesRequest;
import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DTO.ExpedientesResponse;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.business.EditarExpedientesBussinesInterface;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.business.ExpedientesBusinessImplInterface;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.BuscarExpedientesServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.ListarExpedientesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "*")
public class ExpedientesController {

    @Autowired
    ExpedientesBusinessImplInterface IExpedientes;

    @Autowired
    ListarExpedientesServiceInterface IListar;

    @Autowired
    EditarExpedientesBussinesInterface IEditar;



    @PostMapping("/parametrizador/expedientes/listar")
    public ResponseEntity<ExpedientesResponse> ListarExpedientes(@RequestBody ExpedientesRequest request)throws JsonProcessingException{
        ResponseEntity<ExpedientesResponse> response;
        ExpedientesResponse listar = IListar.ListarExpedientes(request);
        if (listar.getOp_Resultado().equals(BigDecimal.valueOf(0))){
            response = new ResponseEntity<>(listar,HttpStatus.OK);
        }
        else if(listar.getOp_Resultado().equals(BigDecimal.valueOf(-1))){
            response = new ResponseEntity<>(listar,HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>(listar,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/parametrizador/expedientes/crear")
    public ResponseEntity<ExpedientesResponse> CrearExpediente(@RequestBody ExpedientesRequest request) throws JsonProcessingException {
        ResponseEntity<ExpedientesResponse> response;
        ExpedientesResponse exped = IExpedientes.ExpedientesBusinessImpl(request);
        if (exped.getOp_Resultado()== BigDecimal.valueOf(0)) {
            response = new ResponseEntity<ExpedientesResponse>(exped, HttpStatus.OK);

        } else if (exped.getOp_Resultado()== BigDecimal.valueOf(-1)) {
            response = new ResponseEntity<ExpedientesResponse>(exped, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<ExpedientesResponse>(exped, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/parametrizador/expedientes/editar")
    public ResponseEntity<ExpedientesResponse> EditarExpedientes(@RequestBody ExpedientesRequest request)throws JsonProcessingException{
        ResponseEntity<ExpedientesResponse> response;
        ExpedientesResponse editar = IEditar.EditarExpedientes(request);
        if (editar.getOp_Resultado().equals(BigDecimal.valueOf(0))){
            response = new ResponseEntity<>(editar,HttpStatus.OK);
        }
        else if(editar.getOp_Resultado().equals(BigDecimal.valueOf(-1))){
            response = new ResponseEntity<>(editar,HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>(editar,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}
