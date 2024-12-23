package com.segurosbolivar.siniestros.parametrizador.causas.service.business.Impl;

import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.CausasResponse;
import com.segurosbolivar.siniestros.parametrizador.causas.service.business.EditarCausaBusinessInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.EditarCausaCiaServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.EditarCausaProdServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.EditarCausaSeccServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EditarCausasBusiness implements EditarCausaBusinessInterface {

@Autowired
EditarCausaCiaServiceInterface editarCiaInterface;

@Autowired
    EditarCausaSeccServiceInterface editarseccInterface;

@Autowired
    EditarCausaProdServiceInterface editarProdInterface;

@Override
    public CausasResponse EditarCausa(CausasRequest request){
        CausasResponse response = new CausasResponse();
        response = editarCiaInterface.EditarCausaPorCia(request);
        if (response.getOp_Resultado()== BigDecimal.valueOf(0)) {
          response= editarseccInterface.EditarCausaPorSecc(request);
            if (response.getOp_Resultado()== BigDecimal.valueOf(0)) {
                response=editarProdInterface.EditarCausaProd(request);
                if (response.getOp_Resultado()== BigDecimal.valueOf(0)) {
                    response.setOp_Resultado(BigDecimal.valueOf(0));
                    response.setOp_MSG("Se modificó satisfactoriamente la causa");
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
