package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.CrearMasivoMaestraServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.MasivoMaestraRequest;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.MasivoMeastraResponse;
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
public class CrearMasivoMestraServiceImpl extends StoredProcedure implements CrearMasivoMaestraServiceInterface {
    @Autowired
    DataSource dataSource;

    public CrearMasivoMestraServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_CrearMasivoMaestro");
        declareParameter(new SqlParameter("ip_codCia", Types.NUMERIC));
        declareParameter(new SqlParameter("ip_codSecc", Types.NUMERIC));
        declareParameter(new SqlParameter("ip_codProducto", Types.NUMERIC));
        declareParameter(new SqlParameter("ip_codAgrupacion", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_idArchivo", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_SecMae",Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_Resultado",Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG",Types.VARCHAR));
    }

    @Override
    public MasivoMeastraResponse execute(MasivoMaestraRequest request){
        Integer Op_SecMae=0;
        Integer Op_Resultado = 0;
        String Op_MSG = "";
        MasivoMeastraResponse response = new MasivoMeastraResponse();
        try {
            Map In = new HashMap<String, Object>();
            In.put("ip_codCia", request.getCodCia());
            In.put("ip_codSecc", request.getCodSecc());
            In.put("ip_codProducto", request.getCodProd());
            In.put("ip_codAgrupacion", request.getCodAgrupacion());
            In.put("ip_idArchivo", request.getIdArchivo());

            Map Out = this.execute(In);
            Op_SecMae = (Integer) Out.get("Op_SecMae");
            Op_Resultado = (Integer) Out.get("Op_Resultado");
            Op_MSG = Out.get("Op_MSG").toString();

            response.setOp_SecMae(Op_SecMae);
            response.setOp_Resultado(Op_Resultado);
            response.setOp_MSG(Op_MSG);
        }
        catch (Exception e){
            response.setOp_Resultado(-1);
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }
}
