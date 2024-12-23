package com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.impl;

import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DAO.ExpedientesRequest;
import com.segurosbolivar.siniestros.parametrizador.expedientes.entity.DTO.ExpedientesResponse;
import com.segurosbolivar.siniestros.parametrizador.expedientes.services.procedure.EditarExpedientesProdServiceInterface;
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
public class EditarExpedientesProdServiceImpl extends StoredProcedure implements EditarExpedientesProdServiceInterface {
    @Autowired
    DataSource dataSource;

    public EditarExpedientesProdServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.EDITAR_EXPEXPROD");
        declareParameter(new SqlParameter("ip_cod_cia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_secc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_producto", Types.INTEGER));
        declareParameter(new SqlParameter("ip_tipo_exped", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_desc_exped", Types.VARCHAR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));

    }

    @Override
    public ExpedientesResponse EditarExpedProd(ExpedientesRequest request){
        ExpedientesResponse response = new ExpedientesResponse();
        BigDecimal OpResultado;
        String OpMSG;
        Map<String, Object> in = new HashMap<>();
        try {
            in.put("ip_cod_cia",request.getCodCia());
            in.put("ip_cod_secc",request.getCodSecc());
            in.put("ip_cod_producto",request.getCodProd());
            in.put("ip_tipo_exped",request.getTipoExped());
            in.put("ip_desc_exped",request.getDescExped());

            Map out = this.execute(in);

            OpResultado = (BigDecimal) out.get("Op_Resultado");
            OpMSG = out.get("Op_MSG").toString();

            response.setOp_Resultado(OpResultado);
            response.setOp_MSG(OpMSG);

        }
        catch (Exception e){
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }


}