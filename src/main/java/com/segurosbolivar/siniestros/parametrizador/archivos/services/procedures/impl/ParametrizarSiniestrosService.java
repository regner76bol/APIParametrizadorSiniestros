package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.impl;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.ParametrizarSiniestrosServiceInterface;
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
public class ParametrizarSiniestrosService extends StoredProcedure implements ParametrizarSiniestrosServiceInterface {

    @Autowired
    DataSource dataSource;

    public ParametrizarSiniestrosService(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_ParametrizarSiniestros");
        declareParameter(new SqlParameter("ip_simulacion", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public ResponseBase execute(){
        ResponseBase response = new ResponseBase();
        BigDecimal Op_Resultado;
        String Op_MSG;

        try {
            Map<String, Object> in = new HashMap();
            in.put("ip_simulacion", BigDecimal.valueOf(0));

            Map<String, Object> out = this.execute(in);

            Op_Resultado = (BigDecimal) out.get("Op_Resultado");
            Op_MSG = out.get("Op_MSG").toString();

            response.setOp_Resultado(Op_Resultado);
            response.setOp_MSG(Op_MSG);

        }catch (Exception e){
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getMessage());

        }
        return response;
    }

}
