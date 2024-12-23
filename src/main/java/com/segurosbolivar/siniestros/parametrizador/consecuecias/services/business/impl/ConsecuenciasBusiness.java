package com.segurosbolivar.siniestros.parametrizador.consecuecias.services.business.impl;

import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ConsecuenciasResponse;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ListarConsecuenciasResponse;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.business.ConsecuenciasBusinessInterface;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConsecuenciasBusiness implements ConsecuenciasBusinessInterface {
    @Autowired
    BuscarConsecuenciaServiceInterface IBuscarConsecuencia;

    @Autowired
    CrearConsecuenciaCiaServiceInterface ICrearConsCia;

    @Autowired
    CrearConsecuenciaSeccServiceInterface ICrearConsSecc;

    @Autowired
    CrearConsecuenciaProdServiceInterface ICrearConsProd;

    @Autowired
    ListarConsecuenciasServiceInterface IListarCons;

    public ListarConsecuenciasResponse ListarConsecuencias(ConsecuenciasRequest request){
        ListarConsecuenciasResponse response = IListarCons.ListarConsecuencias(request);
        if(response == null) {
          response.setOp_Resultado(BigDecimal.valueOf(-1));
          response.setOp_MSG("No se recuperó información de la consulta");
        }

        return response;
    }

    public ConsecuenciasResponse CrearConsecuencia(ConsecuenciasRequest request){
        ConsecuenciasResponse response = IBuscarConsecuencia.Execute(request);
        if (response.getOp_ConsxCia() == 0) {
            ICrearConsCia.execute(request);
            response.setOp_Resultado(BigDecimal.valueOf(0));
            response.setOp_MSG("se ha creado la consecuencia x compañía");
        } else {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG("la Consecuencia x compañía ya existe");
        }

        if (response.getOp_ConsxSecc()==0) {
            ICrearConsSecc.execute(request);
            response.setOp_MSG("se ha creado la consecuencia x sección");
        } else {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG("la Consecuencia x sección ya existe");
        }

        if (response.getOp_ConsxProd()==0) {
            ICrearConsProd.execute(request);
            response.setOp_MSG("se ha creado la consecuencia x producto");
        } else {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG("la Consecuencia x producto ya existe");
        }
        return response;
    }
}
