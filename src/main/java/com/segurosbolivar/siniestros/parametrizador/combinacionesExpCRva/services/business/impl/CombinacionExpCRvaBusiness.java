package com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.business.impl;

import com.segurosbolivar.siniestros.funciones.funcionesInterface;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DAO.CombinacionExpCRvaRequest;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DTO.CombinacionExpCRvaResponse;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.business.CombinacionExpCRvaBusinessInterface;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.procedures.CombinacionExpCrvaServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CombinacionExpCRvaBusiness implements CombinacionExpCRvaBusinessInterface {

    @Autowired
    funcionesInterface Ifunciones;

    @Autowired
    CombinacionExpCrvaServiceInterface ICombinacion;

    public CombinacionExpCRvaResponse CombinacionExpCRva(CombinacionExpCRvaRequest request){
        CombinacionExpCRvaResponse comb = new CombinacionExpCRvaResponse();
        CombinacionExpCRvaResponse response = new CombinacionExpCRvaResponse();

        Integer ExComb = Ifunciones.Fn_ExisteCombExpCRva(request.getCodCia().toString(),request.getCodCob().toString(),request.getCodCausa().toString(),request.getTipoExped(),request.getCodConcepRva().toString());
        if (ExComb==0) {
            Integer ExCau=Ifunciones.Fn_ExisteCausa(request.getCodCia().toString(), request.getCodSecc().toString(), request.getCodProd().toString(), request.getCodCausa().toString());
            if (ExCau==1) {
                Integer ExCob = Ifunciones.Fn_ExisteCobertura(request.getCodCia().toString(), request.getCodProd().toString(),request.getCodCob().toString());
                if (ExCob == 1) {
                    Integer ExExp = Ifunciones.Fn_ExisteExpediente(request.getCodCia().toString(), request.getCodSecc().toString(), request.getCodProd().toString(), request.getTipoExped());
                    if (ExExp == 1) {
                        Integer ExCon = Ifunciones.Fn_ExisteConceptoRva(request.getCodCia().toString(), request.getCodConcepRva().toString());
                        if (ExCon == 1) {
                            comb = ICombinacion.execute(request);
                            if (comb.getOp_Resultado().equals(BigDecimal.valueOf(0))) {
                                response.setOp_Resultado(BigDecimal.valueOf(0));
                                response.setOp_MSG("se ha creado la Combinación expediente- concepto rva satisfactoriamente");
                            } else {
                                response.setOp_Resultado(BigDecimal.valueOf(-1));
                                response.setOp_MSG("No se pudo crear la Combinación expediente- concepto rva");
                            }
                        } else {
                            response.setOp_Resultado(BigDecimal.valueOf(-1));
                            response.setOp_MSG("No existe el concepto de reserva " + request.getCodConcepRva() + " para la creación Combinación expediente- concepto rva");
                        }
                    } else {
                        response.setOp_Resultado(BigDecimal.valueOf(-1));
                        response.setOp_MSG("No existe el tipo expediente " + request.getTipoExped() + " para la creación Combinación expediente- concepto rva");
                    }
                } else {
                    response.setOp_Resultado(BigDecimal.valueOf(-1));
                    response.setOp_MSG("No existe la cobertura "+ request.getCodCob() +" para la creación Combinación expediente- concepto rva");
                }
            }
            else{
                response.setOp_Resultado(BigDecimal.valueOf(-1));
                response.setOp_MSG("No existe la causa "+ request.getCodCausa() +" para la creación Combinación expediente- concepto rva");}
        }
        else{
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG("No existe la combinacion expediente- concepto rva");
        }

       return response;
    }
}
