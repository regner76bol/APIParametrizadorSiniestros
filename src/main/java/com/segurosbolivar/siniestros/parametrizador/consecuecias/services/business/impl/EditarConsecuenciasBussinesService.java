package com.segurosbolivar.siniestros.parametrizador.consecuecias.services.business.impl;

import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ConsecuenciasResponse;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.business.EditarConsecuenciasBussinesInterface;

import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.EditarConsecuenciaCiaServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.EditarConsecuenciasProdServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.EditarConsecuenciasSeccServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;


@Service
public class EditarConsecuenciasBussinesService implements EditarConsecuenciasBussinesInterface {

    @Autowired
    EditarConsecuenciaCiaServiceInterface ciaInterface;

    @Autowired
    EditarConsecuenciasSeccServiceInterface seccInterface;

    @Autowired
    EditarConsecuenciasProdServiceInterface prodInterface;

    @Override
    public ConsecuenciasResponse EditarConsecuenciasBussinesService(ConsecuenciasRequest request){
       ConsecuenciasResponse response;
       response = ciaInterface.EditarConsecuenciasCia(request);
       if (response.getOp_Resultado().equals(BigDecimal.valueOf(0))){
           response = seccInterface.EditarConsecueciasSecc(request);
           if (response.getOp_Resultado().equals(BigDecimal.valueOf(0))){
             response= prodInterface.EditarConsecuenciasProd(request);
             if (response.getOp_Resultado().equals(BigDecimal.valueOf(0))){
                 response.setOp_Resultado(BigDecimal.valueOf(0));
                 response.setOp_MSG("Se modifico satisfactoriamente la consecuencia");
             }
             else{
                 response.setOp_Resultado(BigDecimal.valueOf(-1));
                 response.setOp_MSG(response.getOp_MSG());
             }
           }
           else{
               response.setOp_Resultado(BigDecimal.valueOf(-1));
               response.setOp_MSG(response.getOp_MSG());
           }
       }
       else{
           response.setOp_Resultado(BigDecimal.valueOf(-1));
           response.setOp_MSG(response.getOp_MSG());
       }
       return response;
    }


}
