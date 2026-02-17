package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.ListarLogRequest;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.ListarLogResponse;
import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.ListarLogServiceInterface;
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
public class ListarLogServiceImpl extends StoredProcedure implements ListarLogServiceInterface {
    @Autowired
    DataSource dataSource;

    public ListarLogServiceImpl (DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_ListarLog");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codSecc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codProd", Types.INTEGER));
        declareParameter(new SqlParameter("ip_idParamMae", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_CurLog",Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado",Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG",Types.VARCHAR));

    }

    @Override
    public ListarLogResponse execute(ListarLogRequest request){
        Object Op_CurLog;
        BigDecimal Op_Resultado;
        String Op_MSG;
        ListarLogResponse response= new ListarLogResponse();

        try {
            Map<String, Object> in = new HashMap<>();
            in.put("ip_codCia",request.getCodCia());
            in.put("ip_codSecc",request.getCodSecc());
            in.put("ip_codProd",request.getCodProd());
            in.put("ip_idParamMae",request.getIdParamMae());

            Map<String, Object> out = this.execute(in);

             Op_CurLog = out.get("Op_CurLog");
             Op_Resultado = (BigDecimal) out.get("Op_Resultado");
             Op_MSG = out.get("Op_MSG").toString();

            response.setOp_curLog(Op_CurLog);
            response.setOp_Resultado(Op_Resultado);
            response.setOp_MSG(Op_MSG);


        } catch (Exception e) {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getMessage());
        }
        return response;
    }
}
