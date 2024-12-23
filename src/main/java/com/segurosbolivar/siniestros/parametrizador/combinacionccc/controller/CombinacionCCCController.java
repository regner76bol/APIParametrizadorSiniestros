package com.segurosbolivar.siniestros.parametrizador.combinacionccc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DAO.CombinacionesRequestDAO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DTO.CombinacionCCCResponseDTO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.business.impl.CombinacionCCCBusinessInterface;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.procedures.EditarCombinacionCCCServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.procedures.ListarCombinacionesCCCServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
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

public class CombinacionCCCController {

    @Autowired
    CombinacionCCCBusinessInterface ICombinacion;

    @Autowired
    EditarCombinacionCCCServiceInterface IEditar;

    @Autowired
    ListarCombinacionesCCCServiceInterface IListar;

    @PostMapping("/parametrizador/combccc/listar")
    public ResponseEntity<CombinacionCCCResponseDTO> listarCombinacionCCC(@RequestBody CombinacionesRequestDAO request) throws JsonProcessingException{
        ResponseEntity<CombinacionCCCResponseDTO> response= null;
        CombinacionCCCResponseDTO listar = new CombinacionCCCResponseDTO();
        try {
            listar= IListar.ListarCombCCC(request);
            if (listar.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
                response= new ResponseEntity<CombinacionCCCResponseDTO>(listar, HttpStatus.OK);
            } else if (listar.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
                response= new ResponseEntity<CombinacionCCCResponseDTO>(listar,HttpStatus.OK);
            }
            else{
                response= new ResponseEntity<CombinacionCCCResponseDTO>(listar,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            listar.setOp_Resultado(BigDecimal.valueOf(-1));
            listar.setOp_MSG(e.getCause().getMessage());
            response = new ResponseEntity<CombinacionCCCResponseDTO>(listar,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/parametrizador/combccc/crear")
    public ResponseEntity<CombinacionCCCResponseDTO> CrearCombinacionCCC(@RequestBody CombinacionesRequestDAO request) throws JsonProcessingException{
        ResponseEntity<CombinacionCCCResponseDTO> response= null;
        CombinacionCCCResponseDTO combccc = new CombinacionCCCResponseDTO();
        try {
            combccc= ICombinacion.CombinacionCCCBusiness(request);
            if (combccc.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
                response= new ResponseEntity<CombinacionCCCResponseDTO>(combccc, HttpStatus.OK);
            } else if (combccc.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
                response= new ResponseEntity<CombinacionCCCResponseDTO>(combccc,HttpStatus.OK);
            }
            else{
                response= new ResponseEntity<CombinacionCCCResponseDTO>(combccc,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            combccc.setOp_Resultado(BigDecimal.valueOf(-1));
            combccc.setOp_MSG(e.getCause().getMessage());
            response = new ResponseEntity<CombinacionCCCResponseDTO>(combccc,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/parametrizador/combccc/editar")
    public ResponseEntity<CombinacionCCCResponseDTO> EditarCombinacionCCC(@RequestBody CombinacionesRequestDAO request) throws JsonProcessingException{
        ResponseEntity<CombinacionCCCResponseDTO> response= null;
        CombinacionCCCResponseDTO editar = new CombinacionCCCResponseDTO();
        try {
            editar= IEditar.EditarCombCCC(request);
            if (editar.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
                response= new ResponseEntity<CombinacionCCCResponseDTO>(editar, HttpStatus.OK);
            } else if (editar.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
                response= new ResponseEntity<CombinacionCCCResponseDTO>(editar,HttpStatus.OK);
            }
            else{
                response= new ResponseEntity<CombinacionCCCResponseDTO>(editar,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            editar.setOp_Resultado(BigDecimal.valueOf(-1));
            editar.setOp_MSG(e.getCause().getMessage());
            response = new ResponseEntity<CombinacionCCCResponseDTO>(editar,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

}
