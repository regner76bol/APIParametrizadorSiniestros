package com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.Impl;

import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.CausasResponse;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.CrearCausasCiaServiceInterface;
import org.aspectj.lang.reflect.DeclareParents;
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
public class CrearCausasCiaServiceImpl extends StoredProcedure implements CrearCausasCiaServiceInterface {
@Autowired
    DataSource dataSource;

public CrearCausasCiaServiceImpl(DataSource dataSource){
    super(dataSource,"PKG_PARAMETROS_SINIESTRO.CREAR_CAUSAXCIA");
    declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
    declareParameter(new SqlParameter("ip_tipoCausa", Types.INTEGER));
    declareParameter(new SqlParameter("ip_causa", Types.VARCHAR));
    declareParameter(new SqlParameter("ip_codProducto", Types.INTEGER));
    declareParameter(new SqlParameter("ip_idparammae", Types.INTEGER));
    declareParameter(new SqlParameter("ip_simulacion", Types.INTEGER));
    declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
    declareParameter(new SqlOutParameter("Op_MSG",Types.VARCHAR));
}

@Override
    public CausasResponse execute(CausasRequest request){
    CausasResponse response = new CausasResponse();
    BigDecimal Op_Resultado;
    String Op_MSG;
    try {

        Map in = new HashMap<String,Object>();
        in.put("ip_codCia",request.getCodCia());
        in.put("ip_tipoCausa",request.getTipoCausa());
        in.put("ip_causa",request.getCausa());
        in.put("ip_codProducto",request.getCodProd());
        in.put("ip_idparammae",0);
        in.put("ip_simulacion",0);

        Map out =this.execute(in);
        Op_Resultado = (BigDecimal) out.get("Op_Resultado");
        Op_MSG = out.get("Op_MSG").toString();

        response.setOp_Resultado(Op_Resultado);
        response.setOp_MSG(Op_MSG);

    } catch (Exception e) {
        response.setOp_Resultado(BigDecimal.valueOf(-1));
        response.setOp_MSG(e.getCause().getMessage());
    }
    return response;
}

}
