package com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DTO.ReservaAutomaticaResponse;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.services.procedures.EditarRvaAutomaticaServiceInterface;
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
public class EditarRvaAutomaticaServiceImpl extends StoredProcedure implements EditarRvaAutomaticaServiceInterface {

    @Autowired
    DataSource dataSource;

    public EditarRvaAutomaticaServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.EDITAR_RVAAUTOMATICA");
        declareParameter(new SqlParameter("ip_idExprvaaut", Types.INTEGER));
        declareParameter(new SqlParameter("ip_idTipoReserva", Types.INTEGER));
        declareParameter(new SqlParameter("ip_tipoExped", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codConcepRva", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_codCob", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codCausa", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codCons", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_datoVariable", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_liquidacionAut", Types.VARCHAR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public ReservaAutomaticaResponse EditarRvaAutomatica(ReservaAutomaticaRequest request){
        ReservaAutomaticaResponse response = new ReservaAutomaticaResponse();
        BigDecimal OpResultado;
        String OpMSG;
        Map<String,Object> in = new HashMap<>();
        try {
            in.put("ip_idExprvaaut", request);
            in.put("ip_idTipoReserva", request);
            in.put("ip_tipoExped", request);
            in.put("ip_codConcepRva", request);
            in.put("ip_codCob", request);
            in.put("ip_codCausa", request);
            in.put("ip_codCons", request);
            in.put("ip_datoVariable", request);
            in.put("ip_liquidacionAut", request);

            Map out = this.execute(in);

            OpResultado=(BigDecimal) out.get("Op_Resultado");
            OpMSG=out.get("Op_MSG").toString();

            response.setOp_Resultado(OpResultado);
            response.setOp_MSG(OpMSG);
        }
        catch (Exception e){
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getMessage());
        }
        return response;
    }

}
