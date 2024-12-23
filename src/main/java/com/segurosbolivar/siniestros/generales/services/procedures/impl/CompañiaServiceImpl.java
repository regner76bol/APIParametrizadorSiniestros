package com.segurosbolivar.siniestros.generales.services.procedures.impl;

import com.segurosbolivar.siniestros.generales.entity.DTO.GeneralesResponseDTO;
import com.segurosbolivar.siniestros.generales.services.procedures.CompañiaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Service
public class CompañiaServiceImpl extends StoredProcedure implements CompañiaServiceInterface {

    @Autowired
    DataSource dataSource;

    public CompañiaServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.CARGARCOMPANIA");
        declareParameter(new SqlOutParameter("Op_curCia", Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public GeneralesResponseDTO ListarCompañia(){
        Map in = new HashMap<String,Object>();
        GeneralesResponseDTO response = new GeneralesResponseDTO();
        BigDecimal OpResultado;
        String OpMSG;
        Object OpCurCia;
        try {
            Map out = this.execute(in);
            OpCurCia= out.get("Op_curCia");
            OpResultado= (BigDecimal) out.get("Op_Resultado");
            OpMSG= out.get("Op_MSG").toString();

            response.setOpCurCia(OpCurCia);
            response.setOp_Resultado(OpResultado);
            response.setOp_MSG(OpMSG);

        } catch (Exception e) {

            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }

}
