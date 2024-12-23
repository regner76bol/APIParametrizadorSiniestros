package com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.controller;

import com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.business.impl.CombinacionCCCBusiness;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.business.impl.CombinacionCCCBusinessInterface;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DAO.CombinacionExpCRvaRequest;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DTO.CombinacionExpCRvaResponse;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.business.impl.CombinacionExpCRvaBusiness;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.procedures.EditarCombinacionesExpRvaServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.procedures.ListarCombinacionesExpRvaServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "*")
public class CombinacionExpCRvaController {

    @Autowired
    CombinacionExpCRvaBusiness ICombinacion;

    @Autowired
    ListarCombinacionesExpRvaServiceInterface IListar;

    @Autowired
    EditarCombinacionesExpRvaServiceInterface IEditar;

    @PostMapping("/parametrizador/Combexpcrva/listar")
    public ResponseEntity<CombinacionExpCRvaResponse> ListarCombinacionExpCRva(@RequestBody CombinacionExpCRvaRequest request){
        ResponseEntity<CombinacionExpCRvaResponse> response;
        CombinacionExpCRvaResponse comb;
        try {
            comb = IListar.ListarCombExpRva(request);
            if (comb.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
                response = new ResponseEntity<>(comb, HttpStatus.OK);
            }
            else if (comb.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
                response = new ResponseEntity<>(comb, HttpStatus.OK);
            }
            else {
                response = new ResponseEntity<>(comb, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            CombinacionExpCRvaResponse res = new CombinacionExpCRvaResponse();
            res.setOp_Resultado(BigDecimal.valueOf(-1));
            res.setOp_MSG(e.getCause().getMessage());
            response = new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


    @PostMapping("/parametrizador/Combexpcrva/crear")
    public ResponseEntity<CombinacionExpCRvaResponse> CombinacionExpCRva(@RequestBody CombinacionExpCRvaRequest request){
        ResponseEntity<CombinacionExpCRvaResponse> response;
        CombinacionExpCRvaResponse comb;
        try {
            comb = ICombinacion.CombinacionExpCRva(request);
            if (comb.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
                response = new ResponseEntity<>(comb, HttpStatus.OK);
            }
            else if (comb.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
                response = new ResponseEntity<>(comb, HttpStatus.OK);
            }
            else {
                response = new ResponseEntity<>(comb, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            CombinacionExpCRvaResponse res = new CombinacionExpCRvaResponse();
            res.setOp_Resultado(BigDecimal.valueOf(0));
            res.setOp_MSG(e.getCause().getMessage());
            response = new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/parametrizador/Combexpcrva/editar")
    public ResponseEntity<CombinacionExpCRvaResponse> EditarCombinacionExpCRva(@RequestBody CombinacionExpCRvaRequest request){
        ResponseEntity<CombinacionExpCRvaResponse> response;
        CombinacionExpCRvaResponse edit;
        try {
            edit = IEditar.EditarCombinacionExpRva(request);
            if (edit.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
                response = new ResponseEntity<>(edit, HttpStatus.OK);
            }
            else if (edit.getOp_Resultado().equals(BigDecimal.valueOf(-1))) {
                response = new ResponseEntity<>(edit, HttpStatus.OK);
            }
            else {
                response = new ResponseEntity<>(edit, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            CombinacionExpCRvaResponse res = new CombinacionExpCRvaResponse();
            res.setOp_Resultado(BigDecimal.valueOf(0));
            res.setOp_MSG(e.getCause().getMessage());
            response = new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}
