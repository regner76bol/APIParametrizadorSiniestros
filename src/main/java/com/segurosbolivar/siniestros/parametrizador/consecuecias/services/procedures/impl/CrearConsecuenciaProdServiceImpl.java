package com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ConsecuenciasResponse;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.CrearConsecuenciaProdServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Service
public class CrearConsecuenciaProdServiceImpl extends StoredProcedure implements CrearConsecuenciaProdServiceInterface {

    @Autowired
    DataSource dataSource;

    public CrearConsecuenciaProdServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.CREAR_CONSXPROD");
        declareParameter(new SqlParameter("ip_cod_cia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_secc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_prod", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_cons", Types.INTEGER));
        declareParameter(new SqlParameter("ip_idparammae", Types.INTEGER));
        declareParameter(new SqlParameter("ip_simulacion", Types.INTEGER));
    }

    public ConsecuenciasResponse execute(ConsecuenciasRequest request){
        Map in = new HashMap<String,Object>();
        ConsecuenciasResponse response = new ConsecuenciasResponse();

        try {


            in.put("ip_cod_cia", request.getCodCia());
            in.put("ip_cod_secc", request.getCodSecc());
            in.put("ip_cod_prod", request.getCodProd());
            in.put("ip_cod_cons", request.getCodCons());
            in.put("ip_idparammae", request.getIdParamMae());
            in.put("ip_simulacion", request.getSimulacion());

            Map out = this.execute(in);

            response.setOp_Resultado(BigDecimal.valueOf(0));
            response.setOp_MSG("Ok");

        } catch (Exception e) {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }

}
