package com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.business.impl;

import com.segurosbolivar.siniestros.funciones.funcionesInterface;

import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DAO.CombinacionesRequestDAO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DTO.CombinacionCCCResponseDTO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.procedures.CombinacionCCCServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class CombinacionCCCBusiness implements CombinacionCCCBusinessInterface {

    @Autowired
    CombinacionCCCServiceInterface ICombinacion;

    @Autowired
    funcionesInterface IFunciones;

    public CombinacionCCCResponseDTO CombinacionCCCBusiness(CombinacionesRequestDAO request){
        CombinacionCCCResponseDTO response = new CombinacionCCCResponseDTO();
        Integer combCCC = IFunciones.Fn_ExisteCombCCC(request.getCodCia().toString(),request.getCodSecc().toString(),request.getCodProd().toString(),request.getCodCausa().toString(),request.getCodConsecuencia().toString());
        if (combCCC==0) {
            Integer ExCau = IFunciones.Fn_ExisteCausa(request.getCodCia().toString(),request.getCodSecc().toString(),request.getCodProd().toString(),request.getCodCausa().toString());
            if (ExCau == 1) {
                Integer ExCons = IFunciones.Fn_ExisteConsecuencia(request.getCodCia().toString(),request.getCodSecc().toString(),request.getCodProd().toString(),request.getCodConsecuencia().toString());
                if (ExCons == 1) {
                    Integer ExCob = IFunciones.Fn_ExisteCobertura(request.getCodCia().toString(),request.getCodProd().toString(),request.getCodCob().toString());
                    if (ExCob==1) {
                        response = ICombinacion.execute(request);//crea la combinación CCC
                        if (response.getOp_Resultado().equals(BigDecimal.valueOf(0))){
                            response.setOp_MSG("Se ha creado la combinación CCC Satisfactoriamente");
                        }
                        else {
                            response.setOp_Resultado(BigDecimal.valueOf(-1));
                            response.setOp_MSG("No se pudo crear la combinación CCC");
                        }
                    }
                    else {
                        response.setOp_Resultado(BigDecimal.valueOf(-1));
                        response.setOp_MSG("No existe la cobertura "+ request.getCodCob() +" para crear la Combinación CCC");

                    }
                }
                else {
                    //cons
                    response.setOp_Resultado(BigDecimal.valueOf(-1));
                    response.setOp_MSG("No existe la consecuencia "+ request.getCodConsecuencia() +" para crear la Combinación CCC");

                }
            } else {
                //causa
                response.setOp_Resultado(BigDecimal.valueOf(-1));
                response.setOp_MSG("No existe la causa "+ request.getCodCausa() +" para crear la Combinación CCC");

            }
        }
        else{
            //combinacion
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG("Ya existe la Combinacion CCC");

        }
        return response;
    }


}
