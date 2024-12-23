package com.segurosbolivar.siniestros.parametrizador.consecuecias.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.segurosbolivar.siniestros.funciones.funcionesInterface;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ConsecuenciasResponse;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ListarConsecuenciasResponse;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.business.ConsecuenciasBusinessInterface;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.business.EditarConsecuenciasBussinesInterface;
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
public class ConsecuenciasController {

    @Autowired
    ConsecuenciasBusinessInterface ICrearConsBusiness;

    @Autowired
    EditarConsecuenciasBussinesInterface IEditarBussines;

    @Autowired
    funcionesInterface IFunciones;

    @PostMapping("/parametrizador/consecuencias/listar")
    public ResponseEntity<ListarConsecuenciasResponse> getListarConsecuencias(@RequestBody ConsecuenciasRequest request) throws JsonProcessingException{
        ResponseEntity<ListarConsecuenciasResponse> response;
        ListarConsecuenciasResponse lista = ICrearConsBusiness.ListarConsecuencias(request);
        if (lista.getOp_Resultado().equals(BigDecimal.valueOf(0)) ){
            response = new ResponseEntity<>(lista,HttpStatus.OK);
        }else if (lista.getOp_Resultado().equals(BigDecimal.valueOf(-1)) ) {
            response = new ResponseEntity<>(lista, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/parametrizador/consecuencias/buscarcodigo")
    public ResponseEntity<Integer> getBuscarCodCons(@RequestBody ConsecuenciasRequest request){
        ResponseEntity<Integer> response;
        Integer codCons = IFunciones.fn_BuscarCodigoConsecuencia(request);
        if (codCons > 0){
            response = new ResponseEntity<>(codCons,HttpStatus.OK);
        }
         else if(codCons==0) {
            response = new ResponseEntity<>(codCons,HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<Integer>(codCons,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }

    @PostMapping("/parametrizador/consecuencias/crear")
    public ResponseEntity<ConsecuenciasResponse> postCrearConsecuencia(@RequestBody ConsecuenciasRequest request) throws JsonProcessingException {
        ResponseEntity<ConsecuenciasResponse> response;
        ConsecuenciasResponse cons = ICrearConsBusiness.CrearConsecuencia(request);
        if (cons.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
            response = new ResponseEntity<>(cons, HttpStatus.OK);
        }else if (cons.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
            response = new ResponseEntity<>(cons, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>(cons, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/parametrizador/consecuencias/editar")
    public ResponseEntity<ConsecuenciasResponse>  EditarConsecuencias(@RequestBody ConsecuenciasRequest request) throws JsonProcessingException {
        ResponseEntity<ConsecuenciasResponse> response;
        ConsecuenciasResponse cons = IEditarBussines.EditarConsecuenciasBussinesService(request);
        if (cons.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
            response = new ResponseEntity<>(cons, HttpStatus.OK);
        }else if (cons.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
            response = new ResponseEntity<>(cons, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>(cons, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}
