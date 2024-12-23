package com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ListarConsecuenciasResponse;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.ListarConsecuenciasServiceInterface;
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
public class ListarConsecuenciasService extends StoredProcedure implements ListarConsecuenciasServiceInterface {

    @Autowired
    DataSource dataSource;

    public ListarConsecuenciasService(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_ListarConsecuencias");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codSecc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codProd", Types.INTEGER));
        declareParameter(new SqlOutParameter("op_curCons", Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public ListarConsecuenciasResponse ListarConsecuencias(ConsecuenciasRequest request) {
        Map in = new HashMap<String,Object>();
        ListarConsecuenciasResponse response = new ListarConsecuenciasResponse();
        BigDecimal OpResultado;
        String OpMSG;
        Object opCurCons;

        try {
            in.put("ip_codCia", request.getCodCia());
            in.put("ip_codSecc", request.getCodSecc());
            in.put("ip_codProd", request.getCodProd());

            Map out = this.execute(in);
            opCurCons = out.get("op_curCons");
            OpResultado = (BigDecimal) out.get("Op_Resultado");
            OpMSG = out.get("Op_MSG").toString();

            response.setOpCurCons(opCurCons);
            response.setOp_MSG(OpMSG);
            response.setOp_Resultado(OpResultado);


        }
        catch (Exception e){
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;

    }

}
