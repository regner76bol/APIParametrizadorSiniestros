package com.segurosbolivar.siniestros.parametrizador.causas.service.business.Impl;

import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.CausasResponse;
import com.segurosbolivar.siniestros.parametrizador.causas.service.business.BorrarCausaBusinessInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.BorrarCausaCiaServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.BorrarCausaProdServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.BorrarCausaSeccServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BorrarCausaBusiness implements BorrarCausaBusinessInterface {
    @Autowired
    BorrarCausaCiaServiceInterface borrarCiaInterface;
    @Autowired
    BorrarCausaSeccServiceInterface borrarSeccInterface;
    @Autowired
    BorrarCausaProdServiceInterface borrarProdInterface;

    @Override
    public CausasResponse BorrarCausa(CausasRequest request){
        CausasResponse response = null;
        response= borrarCiaInterface.BorrarCausaCia(request);
        if(response.getOp_Resultado()== BigDecimal.valueOf(0)){
            response=borrarSeccInterface.BorrarCausaSecc(request);
            if(response.getOp_Resultado()== BigDecimal.valueOf(0)) {
                response=borrarProdInterface.BorrarCausaProd(request);
                if(response.getOp_Resultado()== BigDecimal.valueOf(0)){
                    response.setOp_Resultado(BigDecimal.valueOf(0));
                    response.setOp_MSG("se ha borrado la causa");
                }
                else {
                    response.setOp_Resultado(BigDecimal.valueOf(-1));
                    response.setOp_MSG(response.getOp_MSG());
                }
            }
            else {
                response.setOp_Resultado(BigDecimal.valueOf(-1));
                response.setOp_MSG(response.getOp_MSG());}
        }
        else {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(response.getOp_MSG());
        }
        return  response;
    }

}
