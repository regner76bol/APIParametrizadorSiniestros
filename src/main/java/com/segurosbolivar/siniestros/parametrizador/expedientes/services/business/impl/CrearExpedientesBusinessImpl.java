package com.segurosbolivar.siniestros.parametrizador.expedientes.services.business.impl;

import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DAO.ExpedientesRequest;
import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DTO.ExpedientesResponse;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.business.ExpedientesBusinessImplInterface;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.ExpedientesCiaServiceImplInterface;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.ExpedientesProdServiceImplInterface;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.ExpedientesServicesImplInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CrearExpedientesBusinessImpl implements ExpedientesBusinessImplInterface {

    @Autowired
    ExpedientesServicesImplInterface IExpedientes;

    @Autowired
    ExpedientesCiaServiceImplInterface IExpedientesCia;

    @Autowired
    ExpedientesProdServiceImplInterface IExpedientesProd;

    public ExpedientesResponse ExpedientesBusinessImpl(ExpedientesRequest request){
        ExpedientesResponse response = IExpedientes.execute(request);
        if (response.getOp_ExpxCia().equals(BigDecimal.valueOf(0))) {
            IExpedientesCia.execute(request);
            response.setOp_Resultado(BigDecimal.valueOf(0));
            response.setOp_MSG("Se ha creado satisfactoriamente el Expediente por Compañía");
            if (response.getOp_ExpxProd().equals(BigDecimal.valueOf(0))) {
                IExpedientesProd.execute(request);
                response.setOp_Resultado(BigDecimal.valueOf(0));
                response.setOp_MSG("Se ha creado satisfactoriamente el Expediente por sección y producto");
            } else {
                response.setOp_Resultado(BigDecimal.valueOf(-1));
                response.setOp_MSG("El Expediente por sección y porducto ya existe");
            }
        } else {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG("El Expediente por Compañía ya existe");
        }


        return response;
    }
}
