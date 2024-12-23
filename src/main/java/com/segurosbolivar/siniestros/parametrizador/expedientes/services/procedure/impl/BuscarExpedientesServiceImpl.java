package com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.impl;

import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DAO.ExpedientesRequest;
import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DTO.ExpedientesResponse;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.BuscarExpedientesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;;import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Service
public class BuscarExpedientesServiceImpl extends StoredProcedure implements BuscarExpedientesServiceInterface {

    @Autowired
    DataSource dataSource;

    public BuscarExpedientesServiceImpl(DataSource dataSource){
        super(dataSource, "PKG_PARAMETROS_SINIESTRO.Proc_BuscarExpedientes");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codSecc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codProd", Types.INTEGER));
        declareParameter(new SqlParameter("ip_TipoExped", Types.VARCHAR));
        declareParameter(new SqlOutParameter("Op_ExpxCia", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_ExpxProd", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public ExpedientesResponse BuscarExpedientes(ExpedientesRequest request){
        ExpedientesResponse response = new ExpedientesResponse();
        BigDecimal OpResultado;
        String OpMsg;
        BigDecimal OpExpCia;
        BigDecimal OpExpProd;
        Map<String,Object> in = new HashMap<>();
        try {
            in.put("ip_codCia",request.getCodCia());
            in.put("ip_codSecc",request.getCodSecc());
            in.put("ip_codProd",request.getCodProd());
            in.put("ip_TipoExped",request.getTipoExped());

            Map out = this.execute(in);

             OpResultado = (BigDecimal) out.get("Op_Resultado");
             OpMsg= out.get("Op_MSG").toString();
             OpExpCia= (BigDecimal)out.get("Op_ExpxCia");
             OpExpProd= (BigDecimal)out.get("Op_ExpxProd");

             response.setOp_Resultado(OpResultado);
             response.setOp_MSG(OpMsg);
             response.setOp_ExpxCia(OpExpCia);
        }
        catch (Exception e){
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }

}
