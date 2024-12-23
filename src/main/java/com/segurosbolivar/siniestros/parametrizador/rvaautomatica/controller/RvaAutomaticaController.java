package com.segurosbolivar.siniestros.parametrizador.rvaautomatica.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DTO.ReservaAutomaticaResponse;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.business.ReservaAutomaticaBusinessInterface;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.procedures.ListarReservasServiceInterface;
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
public class RvaAutomaticaController {

    @Autowired
    ReservaAutomaticaBusinessInterface IReserva;

    @Autowired
    ListarReservasServiceInterface IListar;



    @PostMapping("/parametrizador/rvaautomatica/listar")
    public ResponseEntity<ReservaAutomaticaResponse> ListarRvaAutomatica(@RequestBody ReservaAutomaticaRequest request)throws JsonProcessingException {
        ResponseEntity<ReservaAutomaticaResponse> response;
        ReservaAutomaticaResponse rva = new ReservaAutomaticaResponse();

        try {
            rva = IListar.ListarReservas(request);
            if (rva.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
                response= new ResponseEntity<>(rva,HttpStatus.OK);
            } else if (rva.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
                response= new ResponseEntity<>(rva,HttpStatus.OK);
            }
            else{
                response= new ResponseEntity<>(rva,HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            rva.setOp_Resultado(BigDecimal.valueOf(-1));
            rva.setOp_MSG(e.getCause().getMessage());
            response = new ResponseEntity<>(rva,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(rva, HttpStatus.OK);
    }
    @PostMapping("/parametrizador/rvaautomatica/crear")
    public ResponseEntity<ReservaAutomaticaResponse> CrearRvaAutomatica(@RequestBody ReservaAutomaticaRequest request)throws JsonProcessingException {
       ResponseEntity<ReservaAutomaticaResponse> response= null;
        ReservaAutomaticaResponse rva = new ReservaAutomaticaResponse();

        try {
          rva = IReserva.ReservaBusiness(request);
            if (rva.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
                response= new ResponseEntity<>(rva,HttpStatus.OK);
            } else if (rva.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
                response= new ResponseEntity<>(rva,HttpStatus.OK);
            }
            else{
                response= new ResponseEntity<>(rva,HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            rva.setOp_Resultado(BigDecimal.valueOf(-1));
            rva.setOp_MSG(e.getCause().getMessage());
            response = new ResponseEntity<>(rva,HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(rva, HttpStatus.OK);
    }
}
