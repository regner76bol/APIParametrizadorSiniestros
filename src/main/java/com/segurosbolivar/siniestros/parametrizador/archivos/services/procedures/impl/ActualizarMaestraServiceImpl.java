package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.impl;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.ActualizarMaestraServiceInterface;
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
public class ActualizarMaestraServiceImpl extends StoredProcedure implements ActualizarMaestraServiceInterface {
    @Autowired
    DataSource dataSource;

    public ActualizarMaestraServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.ActualizarMasivoDetalle");
        declareParameter(new SqlParameter("ip_secMae", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public ResponseBase ActualizarMaestra(BigDecimal idSecMae){
        ResponseBase response = new ResponseBase();
        try{
            Map<String, Object> in = new HashMap<>();
            in.put("ip_secMae", idSecMae);
            Map<String,Object> out = this.execute(in);

            response.setOp_Resultado((BigDecimal) out.get("Op_Resultado"));
            response.setOp_MSG(out.get("Op_MSG").toString());

        }
        catch (Exception e){
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getMessage());
        }
        return response;
    }
}
