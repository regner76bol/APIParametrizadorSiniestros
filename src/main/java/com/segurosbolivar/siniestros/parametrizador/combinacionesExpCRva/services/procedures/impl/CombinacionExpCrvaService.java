package com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DAO.CombinacionExpCRvaRequest;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DTO.CombinacionExpCRvaResponse;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.procedures.CombinacionExpCrvaServiceInterface;
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
public class CombinacionExpCrvaService extends StoredProcedure implements CombinacionExpCrvaServiceInterface {

    @Autowired
    DataSource dataSource;

    public CombinacionExpCrvaService(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.CREAR_COMBINACION_EXPCRVA");
        declareParameter(new SqlParameter("ip_cod_cia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_producto", Types.INTEGER));
        declareParameter(new SqlParameter("ip_tipo_exped", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_cod_concep_rva", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_cob", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_causa", Types.INTEGER));
        declareParameter(new SqlParameter("ip_idparammae", Types.INTEGER));
        declareParameter(new SqlParameter("ip_simulacion", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public CombinacionExpCRvaResponse execute(CombinacionExpCRvaRequest request){
        CombinacionExpCRvaResponse response = new CombinacionExpCRvaResponse();
        try {
            Map in = new HashMap<String,Object>();
            in.put("ip_cod_cia",request.getCodCia());
            in.put("ip_cod_producto",request.getCodProd());
            in.put("ip_tipo_exped",request.getTipoExped());
            in.put("ip_cod_concep_rva",request.getCodConcepRva());
            in.put("ip_cod_cob",request.getCodCob());
            in.put("ip_cod_causa",request.getCodCausa());
            in.put("ip_idparammae",request.getIdParamMae());
            in.put("ip_simulacion",request.getSimulacion());

            Map out = this.execute(in);

            response.setOp_Resultado((BigDecimal) out.get("Op_Resultado"));
            response.setOp_MSG(out.get("Op_MSG").toString());
        } catch (Exception e) {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }

}
