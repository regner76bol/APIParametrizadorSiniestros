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
        Integer codCia=0;
        Integer codSecc=0;
        Integer codProd=0;
        Integer codCausa= 0;
        Integer tipoCausa=0;
        Integer CauxCia=0;
        Integer CauxSecc=0;
        Integer CauxProd=0;
        BigDecimal Op_Resultado;
        String Op_MSG;
        CausasResponse causa = new CausasResponse();
        try {
            codCia=request.getCodCia();
            codSecc=request.getCodSecc();
            codProd=request.getCodProd();
            codCausa=request.getCodCausa();
            tipoCausa=request.getTipoCausa();
            Map in = new HashMap<String,Object>();
            in.put("ip_codCia",codCia);
            in.put("ip_codSecc",codSecc);
            in.put("ip_codProd",codProd);
            in.put("ip_codCausa",codCausa);
            in.put("ip_tipoCausa",tipoCausa);
            Map out = this.execute(in);

            CauxCia = (Integer) out.get("Op_CauxCia");
            CauxSecc = (Integer) out.get("Op_CauxSecc");
            CauxProd = (Integer) out.get("Op_CauxProd");
            Op_Resultado= (BigDecimal) out.get("Op_Resultado");
            Op_MSG=out.get("Op_MSG").toString();

            causa.setOp_CauxCia(CauxCia);
            causa.setOp_CauxSecc(CauxSecc);
            causa.setOp_CauxProd(CauxProd);
            causa.setOp_Resultado(Op_Resultado);
            causa.setOp_MSG(Op_MSG);


        } catch (Exception e) {
            causa.setOp_Resultado(BigDecimal.valueOf(-1));
            causa.setOp_MSG(e.getCause().getMessage());

        }
        return causa;
    }
}

