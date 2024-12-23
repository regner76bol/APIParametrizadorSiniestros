package com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.Impl;

import com.segurosbolivar.siniestros.commons.RequestBase;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.ListarCausasResponse;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.ListarCausasServiceInterface;
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
public class ListarCausasService extends StoredProcedure implements ListarCausasServiceInterface {
    @Autowired
    DataSource dataSource;

    public ListarCausasService(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_ListarCausas");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codSecc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codProd", Types.INTEGER));
        declareParameter(new SqlParameter("ip_tipoCausa", Types.INTEGER));
        declareParameter(new SqlOutParameter("op_curCausas", Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));

    }

    @Override
    public ListarCausasResponse ListarCausas(CausasRequest request){
        Map in = new HashMap<String,Object>();
        ListarCausasResponse response = new ListarCausasResponse();
            BigDecimal OpResultado;
            String OpMSG;
            Object OpcurCausas;

        try {
            in.put("ip_codCia", request.getCodCia());
            in.put("ip_codSecc", request.getCodSecc());
            in.put("ip_codProd", request.getCodProd());
            in.put("ip_tipoCausa",request.getTipoCausa());
            Map out = this.execute(in);
            OpcurCausas = out.get("op_curCausas");
            OpResultado = (BigDecimal)out.get("Op_Resultado");
            OpMSG = out.get("Op_MSG").toString();

            response.setOpCurCausas(OpcurCausas);
            response.setOp_Resultado(OpResultado);
            response.setOp_MSG(OpMSG);

        } catch (Exception e) {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }
}
