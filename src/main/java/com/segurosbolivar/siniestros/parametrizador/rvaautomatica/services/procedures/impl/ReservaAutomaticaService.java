package com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DTO.ReservaAutomaticaResponse;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.procedures.ReservaAutomaticaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReservaAutomaticaService extends StoredProcedure implements ReservaAutomaticaServiceInterface {

    @Autowired
    DataSource dataSource;

    public ReservaAutomaticaService(DataSource dataSource){

        super(dataSource,"PKG_PARAMETROS_SINIESTRO.CREAR_RVAAUTOMATICA");
        declareParameter(new SqlParameter("ip_cod_cia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_secc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_producto", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_cob", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_causa", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_cons", Types.INTEGER));
        declareParameter(new SqlParameter("ip_tipo_exped", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_cod_concep_rva", Types.INTEGER));
        declareParameter(new SqlParameter("ip_id_tipo_rva", Types.INTEGER));
        declareParameter(new SqlParameter("ip_dato_variable", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_liq_automatica", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_idparammae", Types.INTEGER));
        declareParameter(new SqlParameter("ip_simulacion", Types.INTEGER));

    }

    @Override
    public ReservaAutomaticaResponse execute(ReservaAutomaticaRequest request){
        ReservaAutomaticaResponse response = new ReservaAutomaticaResponse();
        BigDecimal Op_Resultado;
        String Op_Msg;
        try {
            Map in = new HashMap<String,Object>();
            in.put("ip_cod_cia",Types.INTEGER);
            in.put("ip_cod_secc",Types.INTEGER);
            in.put("ip_cod_producto",Types.INTEGER);
            in.put("ip_cod_cob",Types.INTEGER);
            in.put("ip_cod_causa",Types.INTEGER);
            in.put("ip_cod_cons",Types.INTEGER);
            in.put("ip_tipo_exped",Types.INTEGER);
            in.put("ip_cod_concep_rva",Types.INTEGER);
            in.put("ip_id_tipo_rva",Types.INTEGER);
            in.put("ip_dato_variable",Types.INTEGER);
            in.put("ip_liq_automatica",Types.INTEGER);
            in.put("ip_idparammae",Types.INTEGER);
            in.put("ip_simulacion",Types.INTEGER);

            Map out = this.execute(in);

            response.setOp_Resultado(BigDecimal.valueOf(0));
            response.setOp_MSG("Ok");

        } catch (Exception e) {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }

        return response;
    }
}
