package com.segurosbolivar.siniestros.generales.services.procedures.impl;

import com.segurosbolivar.siniestros.commons.RequestBase;
import com.segurosbolivar.siniestros.generales.entity.DTO.GeneralesResponseDTO;
import com.segurosbolivar.siniestros.generales.services.procedures.CargarCoberturasServiceInterface;
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
public class CargarCoberturasServiceImpl extends StoredProcedure implements CargarCoberturasServiceInterface {

    @Autowired
    DataSource dataSource;

    public CargarCoberturasServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_CargarCoberturas");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codProd", Types.INTEGER));
        declareParameter(new SqlOutParameter("op_curCob",Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado",Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG",Types.VARCHAR));
    }

    @Override
    public GeneralesResponseDTO CargarCobertura(RequestBase request){
        GeneralesResponseDTO response = new GeneralesResponseDTO();
        BigDecimal OpResultado;
        String OpMSG;
        Object OpCurCob;
        Map<String, Object> in = new HashMap<>();
        try {
            in.put("ip_codCia",request.getCodCia());
            in.put("ip_codProd",request.getCodProd());

            Map out = this.execute(in);

            OpCurCob=out.get("op_curCob");
            OpResultado = (BigDecimal) out.get("Op_Resultado");
            OpMSG=out.get("Op_MSG").toString();

            response.setOp_Resultado(OpResultado);
            response.setOp_MSG(OpMSG);
            response.setOpcurCob(OpCurCob);
        }
        catch (Exception e){
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }

}
