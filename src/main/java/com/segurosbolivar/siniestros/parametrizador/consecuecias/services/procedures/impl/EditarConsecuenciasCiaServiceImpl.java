package com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ConsecuenciasResponse;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.EditarConsecuenciaCiaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class EditarConsecuenciasCiaServiceImpl extends StoredProcedure implements EditarConsecuenciaCiaServiceInterface {

    @Autowired
    DataSource dataSource;

    public EditarConsecuenciasCiaServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.EDITAR_CONSXCIA");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codProducto", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codCons", Types.INTEGER));
        declareParameter(new SqlParameter("ip_descCons", Types.VARCHAR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public ConsecuenciasResponse EditarConsecuenciasCia(ConsecuenciasRequest request){
        ConsecuenciasResponse response = new ConsecuenciasResponse();
        BigDecimal OpResultado;
        String OpMSG;
        Map<String,Object> in = new HashMap<>();
        try{
            in.put("ip_codCia", request.getCodCia());
            in.put("ip_codProducto", request.getCodProd());
            in.put("ip_codCons", request.getCodCons());
            in.put("ip_descCons", request.getConsecuencia());

            Map out = this.execute(in);

            OpResultado= (BigDecimal) out.get("Op_Resultado");
            OpMSG = out.get("Op_MSG").toString();

            response.setOp_Resultado(OpResultado);
            response.setOp_MSG(OpMSG);
        }
        catch (Exception e){
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());
        }
        return response;
    }
}
