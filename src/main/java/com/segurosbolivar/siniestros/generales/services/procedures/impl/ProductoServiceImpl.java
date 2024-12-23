package com.segurosbolivar.siniestros.generales.services.procedures.impl;

import com.segurosbolivar.siniestros.commons.RequestBase;
import com.segurosbolivar.siniestros.generales.entity.DTO.GeneralesResponseDTO;
import com.segurosbolivar.siniestros.generales.services.procedures.ProductoServiceInterface;
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
public class ProductoServiceImpl extends StoredProcedure implements ProductoServiceInterface {

    @Autowired
    DataSource dataSource;

    public ProductoServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.CARGARPRODUCTO");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codSecc", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_curProd", Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public GeneralesResponseDTO ListarProducto(RequestBase request){
        Map in = new HashMap<String,Object>();
        GeneralesResponseDTO response = new GeneralesResponseDTO();
        BigDecimal OpResultado;
        String OpMSG;
        Object OpCurProd;
        try {
            in.put("ip_codCia",request.getCodCia());
            in.put("ip_codSecc",request.getCodSecc());
            Map out = this.execute(in);
            OpCurProd= out.get("Op_curProd");
            OpResultado= (BigDecimal) out.get("Op_Resultado");
            OpMSG= out.get("Op_MSG").toString();

            response.setOpCurProd(OpCurProd);
            response.setOp_Resultado(OpResultado);
            response.setOp_MSG(OpMSG);

        } catch (Exception e) {

            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }

}
