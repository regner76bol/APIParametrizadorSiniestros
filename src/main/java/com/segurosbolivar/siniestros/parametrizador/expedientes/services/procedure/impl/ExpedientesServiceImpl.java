package com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.impl;

import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DAO.ExpedientesRequest;
import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DTO.ExpedientesResponse;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.ExpedientesServicesImplInterface;
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
public class ExpedientesServiceImpl extends StoredProcedure implements ExpedientesServicesImplInterface {
    @Autowired
    DataSource dataSource;

    public ExpedientesServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_BuscarExpedientes");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codSecc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codProd", Types.INTEGER));
        declareParameter(new SqlParameter("ip_TipoExped", Types.VARCHAR));
        declareParameter(new SqlOutParameter("Op_ExpxCia",Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_ExpxProd",Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_Resultado",Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG",Types.VARCHAR));
    }

    @Override
    public ExpedientesResponse execute(ExpedientesRequest request){
        ExpedientesResponse response= new ExpedientesResponse();
        BigDecimal Op_ExpxCia;
        BigDecimal Op_ExpxProd;
        BigDecimal Op_Resultado= BigDecimal.valueOf(0);
        String Op_MSG= null;
        try {
            Map in = new HashMap<String,Object>();
            in.put("ip_codCia",request.getCodCia());
            in.put("ip_codSecc",request.getCodSecc());
            in.put("ip_codProd",request.getCodProd());
            in.put("ip_TipoExped",request.getTipoExped());

            Map out = this.execute(in);

            Op_ExpxCia = (BigDecimal) out.get("Op_ExpxCia");
            Op_ExpxProd = (BigDecimal) out.get("Op_ExpxProd");
            Op_Resultado = (BigDecimal) out.get("Op_Resultado");
            Op_MSG= out.get("Op_MSG").toString();

            response.setOp_ExpxCia(Op_ExpxCia);
            response.setOp_ExpxProd(Op_ExpxProd);
            response.setOp_Resultado(Op_Resultado);
            response.setOp_MSG(Op_MSG);

        } catch (Exception e) {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }
}
