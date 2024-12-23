package com.segurosbolivar.siniestros.generales.services.procedures.impl;

import com.segurosbolivar.siniestros.commons.RequestBase;
import com.segurosbolivar.siniestros.generales.entity.DTO.GeneralesResponseDTO;
import com.segurosbolivar.siniestros.generales.services.procedures.CargarConceptosRVAServiceInterface;
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
public class CargarConceptosRVAServiceImpl extends StoredProcedure implements CargarConceptosRVAServiceInterface {

    @Autowired
    DataSource dataSource;

    public CargarConceptosRVAServiceImpl(DataSource dataSource) {
        super(dataSource, "PKG_PARAMETROS_SINIESTRO.Proc_CargarConcepRVA");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlOutParameter("op_curCRVA", Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public GeneralesResponseDTO CargarConceptosRVA(RequestBase request) {
        GeneralesResponseDTO response = new GeneralesResponseDTO();
        BigDecimal OpResultado;
        String OpMSG;
        Object OpcurConRVA;
        Map<String, Object> in = new HashMap<>();
        try {
            in.put("ip_codCia", request.getCodCia());

            Map out = this.execute(in);

            OpcurConRVA = out.get("op_curCRVA");
            OpResultado = (BigDecimal) out.get("Op_Resultado");
            OpMSG = out.get("Op_MSG").toString();

            response.setOp_Resultado(OpResultado);
            response.setOp_MSG(OpMSG);
            response.setOpcurCRVA(OpcurConRVA);
        } catch (Exception e) {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }
}