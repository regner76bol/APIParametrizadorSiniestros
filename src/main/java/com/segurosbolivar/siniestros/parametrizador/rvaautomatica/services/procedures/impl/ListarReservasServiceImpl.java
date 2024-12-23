package com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.procedures.impl;


import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DTO.ReservaAutomaticaResponse;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.procedures.ListarReservasServiceInterface;
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
public class ListarReservasServiceImpl extends StoredProcedure implements ListarReservasServiceInterface {

    @Autowired
    DataSource dataSource;

    public ListarReservasServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_ListarReservaAutomatica");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codSecc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codProd", Types.INTEGER));
        declareParameter(new SqlOutParameter("op_curRva", Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }


    @Override
    public ReservaAutomaticaResponse ListarReservas(ReservaAutomaticaRequest request){
        ReservaAutomaticaResponse response = new ReservaAutomaticaResponse();
        BigDecimal OpResultado;
        String OpMsg;
        Object OpCurRva;
        Map<String, Object> in = new HashMap<>();
        try{
            in.put("ip_codCia",request.getCodCia());
            in.put("ip_codSecc",request.getCodSecc());
            in.put("ip_codProd",request.getCodProd());

            Map out = this.execute(in);

            OpResultado = (BigDecimal) out.get("Op_Resultado");
            OpMsg = out.get("Op_MSG").toString();
            OpCurRva = out.get("op_curRva");

            response.setOp_Resultado(OpResultado);
            response.setOp_MSG(OpMsg);
            response.setOpcurRva(OpCurRva);

        }
        catch (Exception e){
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());

        }
        return response;

    }

}
