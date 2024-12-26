package com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.business.impl;

import com.segurosbolivar.siniestros.funciones.funcionesInterface;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DTO.ReservaAutomaticaResponse;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.business.ReservaAutomaticaBusinessInterface;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.procedures.ReservaAutomaticaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReservaAutomaticaBusiness implements ReservaAutomaticaBusinessInterface {

    @Autowired
    ReservaAutomaticaServiceInterface Ireserva;

    @Autowired
    funcionesInterface Ifunciones;

    public ReservaAutomaticaResponse ReservaBusiness(ReservaAutomaticaRequest request){
        ReservaAutomaticaResponse response =null;

        Integer ExCob = Ifunciones.Fn_ExisteCobertura(request.getCodCia().toString(),request.getCodProd().toString(), request.getCodCob().toString());
        if (ExCob==1) {
            Integer ExCau = Ifunciones.Fn_ExisteCausa(request.getCodCia().toString(),request.getCodSecc().toString(), request.getCodProd().toString(),request.getCodCausa().toString());
            if (ExCau==1) {
                Integer ExCons = Ifunciones.Fn_ExisteConsecuencia(request.getCodCia().toString(),request.getCodSecc().toString(), request.getCodProd().toString(),request.getCodConsecuencia().toString());
                if (ExCons==1){
                    Integer ExExp = Ifunciones.Fn_ExisteExpediente(request.getCodCia().toString(),request.getCodSecc().toString(), request.getCodProd().toString(),request.getTipoExped());
                    if (ExExp==1){
                        Integer ExConRva = Ifunciones.Fn_ExisteConceptoRva(request.getCodCia().toString(), request.getConcepRva().toString());
                        if (ExConRva==1) {
                            Integer ExTipoRva = Ifunciones.Fn_ExisteTipoRva(request.getCodCia().toString(),request.getTipoRva().toString());
                            if (ExTipoRva==1){
                                response = Ireserva.execute(request);
                                if (response.getOp_Resultado().equals(BigDecimal.valueOf(0))){
                                     response.setOp_MSG("Se ha creado la Reserva Automática Satisfactoriamente");
                                }
                                else {
                                    response.setOp_Resultado(BigDecimal.valueOf(-1));
                                    response.setOp_MSG(response.getOp_MSG());
                                }
                            }
                            else{
                                response.setOp_Resultado(BigDecimal.valueOf(-1));
                                response.setOp_MSG("No existe el tipo reserva "+ request.getTipoRva() +" para crear la Reserva Automática");
                            }
                        }
                        else{
                            response.setOp_Resultado(BigDecimal.valueOf(-1));
                            response.setOp_MSG("No existe el concepto de reserva "+ request.getConcepRva() +" para crear la Reserva Automática");
                        }
                    }
                    else{
                        response.setOp_Resultado(BigDecimal.valueOf(-1));
                        response.setOp_MSG("No existe el expediente "+ request.getTipoExped() +" para crear la Reserva Automática");
                    }
                }
                else{
                    response.setOp_Resultado(BigDecimal.valueOf(-1));
                    response.setOp_MSG("No existe la consecuencia"+ request.getCodConsecuencia()+" para crear la Reserva Automática");
                }
            }
            else{
                response.setOp_Resultado(BigDecimal.valueOf(-1));
                response.setOp_MSG("No existe la causa "+ request.getCodCausa() +" para crear la Reserva Automática");
            }
        }
        else{
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG("No existe la cobertura "+ request.getCodCob() +" para crear la Reserva Automática");
        }
        return response;
    }
}
