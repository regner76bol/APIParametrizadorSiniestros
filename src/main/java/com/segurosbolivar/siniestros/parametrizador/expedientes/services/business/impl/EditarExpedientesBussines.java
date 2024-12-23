package com.segurosbolivar.siniestros.parametrizador.expedientes.services.business.impl;

import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.EditarConsecuenciaCiaServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.EditarConsecuenciasProdServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DAO.ExpedientesRequest;
import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DTO.ExpedientesResponse;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.business.EditarExpedientesBussinesInterface;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.EditarExpedienteCiaServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.EditarExpedientesProdServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EditarExpedientesBussines implements EditarExpedientesBussinesInterface {
    @Autowired
    EditarExpedienteCiaServiceInterface ExpedCiaInterface;

    @Autowired
    EditarExpedientesProdServiceInterface ExpedProdInterface;

    @Override
    public ExpedientesResponse EditarExpedientes(ExpedientesRequest request){

        ExpedientesResponse response = new ExpedientesResponse();
        response = ExpedCiaInterface.EditarExpedCia(request);
        if (response.getOp_Resultado().equals(BigDecimal.valueOf(0))){
            response = ExpedProdInterface.EditarExpedProd(request);
            if (response.getOp_Resultado().equals(BigDecimal.valueOf(0))){
                response.setOp_Resultado(BigDecimal.valueOf(0));
                response.setOp_MSG("Se ha editado satisfactoriamente el expediente");
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
