package com.segurosbolivar.siniestros.parametrizador.relexprecupero.service.procedure.impl;

import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DAO.RelExpRecuperoRequest;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DTO.RelExpRecuperoResponse;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.service.procedure.CrearRelExpRecaudoServiceInterface;
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
public class CrearRelExpRecaudoServiceImpl extends StoredProcedure implements CrearRelExpRecaudoServiceInterface {

    @Autowired
    DataSource dataSource;

    public CrearRelExpRecaudoServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.CREAR_REL_EXPXRECUPERO");
        declareParameter(new SqlParameter("ip_cod_cia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_secc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_prod", Types.INTEGER));
        declareParameter(new SqlParameter("ip_tipo_exped", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_tipo_exp_relac", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_mca_relac", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_idparammae", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG",Types.VARCHAR));
    }

@Override
    public RelExpRecuperoResponse execute(RelExpRecuperoRequest request) {

    RelExpRecuperoResponse response = new RelExpRecuperoResponse();
    BigDecimal Op_Resultado;

    String Op_MSG;

    try {

        Map in = new HashMap<String, Object>();
        in.put("ip_cod_cia", request.getCodCia());
        in.put("ip_cod_secc", request.getCodSecc());
        in.put("ip_cod_prod", request.getCodProd());
        in.put("ip_tipo_exped", request.getTipoExped());
        in.put("ip_tipo_exp_relac", request.getTipoExpRelac());
        in.put("ip_mca_relac", request.getMcaRelac());

        Map out = this.execute(in);

        Op_Resultado = (BigDecimal) out.get("Op_Resultado");
        Op_MSG = out.get("Op_MSG").toString();

        response.setOp_Resultado(Op_Resultado);
        response.setOp_MSG(Op_MSG);


    } catch (Exception e) {
        response.setOp_Resultado(BigDecimal.valueOf(-1));
        response.setOp_MSG(e.getCause().getMessage());

    }

    return response;

}
}
