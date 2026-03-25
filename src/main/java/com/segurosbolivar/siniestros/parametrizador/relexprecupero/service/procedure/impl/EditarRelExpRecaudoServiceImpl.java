package com.segurosbolivar.siniestros.parametrizador.relexprecupero.service.procedure.impl;

import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DAO.RelExpRecuperoRequest;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DTO.RelExpRecuperoResponse;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.service.procedure.EditarRelExpRecaudoServiceInterface;
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
public class EditarRelExpRecaudoServiceImpl extends StoredProcedure implements EditarRelExpRecaudoServiceInterface {

    @Autowired
    DataSource dataSource;

    public EditarRelExpRecaudoServiceImpl(DataSource dataSource) {
        super(dataSource, "PKG_PARAMETROS_SINIESTRO.EDITAR_REL_EXPXRECUPERO");
        declareParameter(new SqlParameter("ip_cod_cia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_secc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_producto", Types.INTEGER));
        declareParameter(new SqlParameter("ip_tipo_exped", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_tipo_exp_relac", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_mca_relac", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_tipo_exped_old", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_tipo_exp_relac_old", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_idparammae", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public RelExpRecuperoResponse execute(RelExpRecuperoRequest request) {

        RelExpRecuperoResponse response = new RelExpRecuperoResponse();

        try {

            Map<String, Object> in = new HashMap<>();
            in.put("ip_cod_cia", request.getCodCia());
            in.put("ip_cod_secc", request.getCodSecc());
            in.put("ip_cod_producto", request.getCodProd());
            in.put("ip_tipo_exped", request.getTipoExped());
            in.put("ip_tipo_exp_relac", request.getTipoExpRelac());
            in.put("ip_mca_relac", request.getMcaRelac());
            in.put("ip_tipo_exped_old", request.getTipoExpedOld());
            in.put("ip_tipo_exp_relac_old", request.getTipoExpRelacOld());
            in.put("ip_idparammae", request.getIdParamMae());

            Map<String, Object> out = this.execute(in);

            response.setOp_Resultado((BigDecimal) out.get("Op_Resultado"));
            response.setOp_MSG(out.get("Op_MSG").toString());


        } catch (Exception e) {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getMessage());
        }
        return response;
    }
}
