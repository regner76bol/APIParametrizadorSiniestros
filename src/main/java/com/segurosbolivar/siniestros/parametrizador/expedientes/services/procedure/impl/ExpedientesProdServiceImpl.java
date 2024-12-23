package com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.impl;

import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DAO.ExpedientesRequest;
import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DTO.ExpedientesResponse;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.ExpedientesProdServiceImplInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExpedientesProdServiceImpl extends StoredProcedure implements ExpedientesProdServiceImplInterface {
    @Autowired
    DataSource dataSource;

    public ExpedientesProdServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.CREAR_EXPXPROD");
        declareParameter(new SqlParameter("ip_cod_cia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_secc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_producto", Types.INTEGER));
        declareParameter(new SqlParameter("ip_tipo_exped", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_desc_exped", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_idparammae", Types.INTEGER));
        declareParameter(new SqlParameter("ip_simulacion", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public ExpedientesResponse execute(ExpedientesRequest request){
        Map in = new HashMap<String,Object>();
        ExpedientesResponse response = new ExpedientesResponse();
        try {
            in.put("ip_cod_cia",request.getCodCia());
            in.put("ip_cod_secc",request.getCodSecc());
            in.put("ip_cod_producto",request.getCodProd());
            in.put("ip_tipo_exped",request.getTipoExped());
            in.put("ip_desc_exped",request.getDescExped());
            in.put("ip_idparammae",request.getIdParamMae());
            in.put("ip_simulacion",request.getSimulacion());

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
