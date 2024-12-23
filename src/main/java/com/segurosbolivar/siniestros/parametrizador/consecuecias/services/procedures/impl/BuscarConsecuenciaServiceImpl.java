package com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ConsecuenciasResponse;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.BuscarConsecuenciaServiceInterface;
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
public class BuscarConsecuenciaServiceImpl extends StoredProcedure implements BuscarConsecuenciaServiceInterface {

    @Autowired
    DataSource dataSource;
    public BuscarConsecuenciaServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_BuscarConsecuencias");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codSecc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codProd", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codCons", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_ConsxCia",Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_ConsxSecc",Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_ConsxProd",Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_Resultado",Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG",Types.VARCHAR));
    }

    public ConsecuenciasResponse Execute(ConsecuenciasRequest request){
        Integer Op_ConsxCia=0;
        Integer Op_ConsxSecc =0;
        Integer Op_ConsxProd=0;
        BigDecimal Op_Resultado;
        String Op_MSG;

        ConsecuenciasResponse response = new ConsecuenciasResponse();

        try {


            Map in = new HashMap<String, Object>();
            in.put("ip_codCia", request.getCodCia());
            in.put("ip_codSecc", request.getCodSecc());
            in.put("ip_codProd", request.getCodProd());
            in.put("ip_codCons", request.getCodCons());

            Map out = this.execute(in);

            Op_ConsxCia = (Integer) out.get("Op_ConsxCia");
            Op_ConsxSecc = (Integer) out.get("Op_ConsxSecc");
            Op_ConsxProd = (Integer) out.get("Op_ConsxProd");
            Op_Resultado = (BigDecimal) out.get("Op_Resultado");
            Op_MSG = out.get("Op_MSG").toString();

            response.setOp_ConsxCia(Op_ConsxCia);
            response.setOp_ConsxSecc(Op_ConsxSecc);
            response.setOp_ConsxProd(Op_ConsxProd);
            response.setOp_Resultado(Op_Resultado);
            response.setOp_MSG(Op_MSG);

        } catch (Exception e) {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());

        }

        return response;


    }
}
