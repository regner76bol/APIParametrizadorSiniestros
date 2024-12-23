package com.segurosbolivar.siniestros.parametrizador.causas.service.business.Impl;

import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.CausasResponse;
import com.segurosbolivar.siniestros.parametrizador.causas.service.business.CrearCausasBusinessInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.CausasServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.CrearCausasCiaServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.CrearCausasProdServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.CrearCausasSeccServiceInterface;
import com.segurosbolivar.siniestros.funciones.funcionesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CrearCausasBusiness implements CrearCausasBusinessInterface {

    @Autowired
     CausasServiceInterface causasInterface;
    @Autowired
     CrearCausasCiaServiceInterface ciaInterface;

    @Autowired
     CrearCausasSeccServiceInterface seccInterface;

    @Autowired
    CrearCausasProdServiceInterface prodInterface;

    @Autowired
    funcionesInterface IFunciones;
    @Override
    public CausasResponse CrearCausa(CausasRequest request){
        CausasResponse response = causasInterface.execute(request);

        if (response.getOp_CauxCia() == 0) {
            CausasResponse cauCia = ciaInterface.execute(request);
            //Validación y creación de causas por compañía
            if (cauCia.getOp_Resultado()== BigDecimal.valueOf(0)) {
                response.setOp_Resultado(BigDecimal.valueOf(0));
                response.setOp_MSG("se ha creado la Causa x compañía");
                //validacion y creacion de causa por sección
                if (response.getOp_CauxSecc() == 0) {
                    CausasResponse cauSecc = seccInterface.execute(request);
                    if (cauSecc.getOp_Resultado()== BigDecimal.valueOf(0)){
                        response.setOp_Resultado(BigDecimal.valueOf(0));
                        response.setOp_MSG("se ha creado la Causa x sección");
                        //validación y creación de causas por producto
                        if (response.getOp_CauxProd()==0) {
                            CausasResponse cauProd = prodInterface.execute(request);
                            if (cauProd.getOp_Resultado()== BigDecimal.valueOf(0)){
                                response.setOp_Resultado(BigDecimal.valueOf(0));
                                response.setOp_MSG("se ha creado la Causa x producto");
                            }
                            else {
                                response.setOp_Resultado(BigDecimal.valueOf(-1));
                                response.setOp_MSG(cauProd.getOp_MSG());
                            }
                        } else {
                            response.setOp_Resultado(BigDecimal.valueOf(-1));
                            response.setOp_MSG("la Causa x producto ya existe");
                        }
                    }
                    else{
                        response.setOp_Resultado(BigDecimal.valueOf(-1));
                        response.setOp_MSG(cauSecc.getOp_MSG());
                    }
                } else {
                    response.setOp_Resultado(BigDecimal.valueOf(-1));
                    response.setOp_MSG("la Causa x sección ya existe");
                }
            }
            else{
                response.setOp_Resultado(cauCia.getOp_Resultado());
                response.setOp_MSG(cauCia.getOp_MSG());
            }

        } else {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG("la Causa x compañía ya existe");
        }
        return response;
    }

    @Override
    public Integer BuscarCodigoCausa(CausasRequest request){
        Integer codCausa = IFunciones.Fn_BuscarCodigoCausa(request);
        return  codCausa;
    }



}
