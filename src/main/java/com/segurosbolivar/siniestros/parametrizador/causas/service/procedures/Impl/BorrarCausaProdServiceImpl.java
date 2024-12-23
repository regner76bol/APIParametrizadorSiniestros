package com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.Impl;

import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.CausasResponse;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.BorrarCausaProdServiceInterface;
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
public class BorrarCausaProdServiceImpl extends StoredProcedure implements BorrarCausaProdServiceInterface {
    @Autowired
    DataSource dataSource;

    public BorrarCausaProdServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.BORRAR_CAUSAXPROD");
        declareParameter(new SqlParameter("ip_idCausaProd", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public CausasResponse BorrarCausaProd(CausasRequest request){
        CausasResponse response = new CausasResponse();
        BigDecimal Op_Resultado;
        String Op_MSG;
        try{
            Map in = new HashMap<String,Object>();
            in.put("ip_idCausaProd",request.getIdCausaProd());

            Map out = this.execute(in);
            Op_Resultado= (BigDecimal) out.get("Op_Resultado");
            Op_MSG= out.get("Op_MSG").toString();

            response.setOp_Resultado(Op_Resultado);
            response.setOp_MSG(Op_MSG);
        }
        catch (Exception e){
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }
}
