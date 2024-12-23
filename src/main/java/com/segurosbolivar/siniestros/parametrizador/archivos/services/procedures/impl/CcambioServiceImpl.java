package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.CcambioRequest;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.CcambioResponse;
import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.CcambioServiceInterface;
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
public class CcambioServiceImpl extends StoredProcedure implements CcambioServiceInterface {

    @Autowired
    DataSource dataSource;

    public CcambioServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_ControlDeCambio");
        declareParameter(new SqlParameter("ip_codCia", Types.NUMERIC));
        declareParameter(new SqlParameter("ip_codSecc", Types.NUMERIC));
        declareParameter(new SqlParameter("ip_codProd", Types.NUMERIC));
        declareParameter(new SqlParameter("ip_idParamMae", Types.NUMERIC));
        declareParameter(new SqlParameter("ip_tipo", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_CurCCambio",Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado",Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG",Types.VARCHAR));
    }

    @Override
    public CcambioResponse excecute(CcambioRequest request){
        BigDecimal Op_Resultado;
        String Op_MSG;
        Object Op_CurCCambio;
        CcambioResponse response= new CcambioResponse();
        try {
            Map in = new HashMap<String, Object>();
            in.put("ip_codCia", request.getCodCia());
            in.put("ip_codSecc", request.getCodSecc());
            in.put("ip_codProd", request.getCodProd());
            in.put("ip_idParamMae", request.getIdParamMae());
            in.put("ip_tipo", request.getTipo());

            Map out = this.execute(in);

            Op_CurCCambio = out.get("Op_CurCCambio");
            Op_Resultado = (BigDecimal) out.get("Op_Resultado");
            Op_MSG = out.get("Op_MSG").toString();

            response.setOp_CurCCambio(Op_CurCCambio);
            response.setOp_Resultado(Op_Resultado);
            response.setOp_MSG(Op_MSG);

        } catch (Exception e) {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }


}
