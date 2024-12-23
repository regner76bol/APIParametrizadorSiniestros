package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.ActualizarMasivoRequest;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.ActualizarMasivoResponse;
import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.ActualizarMasivoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Service
public class ActualizarMasivoServiceImpl extends StoredProcedure implements ActualizarMasivoServiceInterface {
    @Autowired
    DataSource dataSource;

    public ActualizarMasivoServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.ActualizarMasivoDetalle");
        declareParameter(new SqlParameter("ip_secMae", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_Resultado",Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG",Types.VARCHAR));
    }

    @Override
    public ActualizarMasivoResponse execute(ActualizarMasivoRequest request){
        Integer Op_Resultado = 0;
        String Op_MSG = "";
        ActualizarMasivoResponse response = new ActualizarMasivoResponse();

        try {
            Map In = new HashMap<String, Object>();
            In.put("Ip_secMae", request.getSecMae());

            Map Out = this.execute(In);
            Op_Resultado = (Integer) Out.get("Op_Resultado");
            Op_MSG = Out.get("Op_MSG").toString();
        }
        catch (Exception e){
            response.setOp_Resultado(-1);
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }
}
