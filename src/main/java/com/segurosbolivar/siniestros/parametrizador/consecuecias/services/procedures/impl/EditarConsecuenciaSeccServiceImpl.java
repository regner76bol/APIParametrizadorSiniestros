package com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ConsecuenciasResponse;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.EditarConsecuenciasSeccServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Service
public class EditarConsecuenciaSeccServiceImpl extends StoredProcedure implements EditarConsecuenciasSeccServiceInterface {

    @Autowired
    DataSource dataSource;

    public EditarConsecuenciaSeccServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.EDITAR_CONSXSECC");
        declareParameter(new SqlParameter("ip_cod_cia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_secc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_producto", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_cons", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public ConsecuenciasResponse EditarConsecueciasSecc(ConsecuenciasRequest request){
        ConsecuenciasResponse response = new ConsecuenciasResponse();
        BigDecimal OpResultado;
        String OpMSG;
        Map<String, Object> in = new HashMap<>();
        try{

            in.put("ip_cod_cia", request.getCodCia());
            in.put("ip_cod_secc", request.getCodSecc());
            in.put("ip_cod_producto", request.getCodProd());
            in.put("ip_cod_cons", request.getCodCons());

            Map out = this.execute(in);

            OpResultado = (BigDecimal) out.get("Op_Resultado");
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
