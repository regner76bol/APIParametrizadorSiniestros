package com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.Impl;

import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.CausasResponse;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.CausasServiceInterface;
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
public class CausasServiceImpl extends StoredProcedure implements CausasServiceInterface {

    @Autowired
    DataSource dataSource;

    public CausasServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_BuscarCausas");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codSecc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codProd", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codCausa", Types.INTEGER));
        declareParameter(new SqlParameter("ip_tipoCausa", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_CauxCia", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_CauxSecc", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_CauxProd", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));

    }

    @Autowired
    public CausasResponse execute(CausasRequest request){

        BigDecimal Op_Resultado;
        String Op_MSG;
        CausasResponse causa = new CausasResponse();
        try {

            Map<String,Object> in = new HashMap<>();
            in.put("ip_codCia",request.getCodCia());
            in.put("ip_codSecc",request.getCodSecc());
            in.put("ip_codProd",request.getCodProd());
            in.put("ip_codCausa",request.getCodCausa());
            in.put("ip_tipoCausa",request.getTipoCausa());

            Map<String,Object> out = this.execute(in);

            Op_Resultado= (BigDecimal) out.get("Op_Resultado");
            Op_MSG=out.get("Op_MSG").toString();

            causa.setOp_CauxCia((Integer) out.get("Op_CauxCia"));
            causa.setOp_CauxSecc((Integer) out.get("Op_CauxSecc"));
            causa.setOp_CauxProd((Integer) out.get("Op_CauxProd"));
            causa.setOp_Resultado(Op_Resultado);
            causa.setOp_MSG(Op_MSG);
        } catch (Exception e) {
            causa.setOp_Resultado(BigDecimal.valueOf(-1));
            causa.setOp_MSG(e.getMessage());
        }
        return causa;
    }
}

