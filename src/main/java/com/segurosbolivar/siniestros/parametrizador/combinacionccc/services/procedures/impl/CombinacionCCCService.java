package com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DAO.CombinacionesRequestDAO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DTO.CombinacionCCCResponseDTO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.procedures.CombinacionCCCServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
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
public class CombinacionCCCService extends StoredProcedure implements CombinacionCCCServiceInterface {

    @Autowired
    DataSource dataSource;

    public CombinacionCCCService(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.CREAR_COMBINACION_CCC");
        declareParameter(new SqlParameter("ip_cod_cia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_secc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_producto", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_cob", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_causa", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_cons", Types.INTEGER));
        declareParameter(new SqlParameter("ip_idparammae", Types.INTEGER));
        declareParameter(new SqlParameter("ip_simulacion", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public CombinacionCCCResponseDTO execute(CombinacionesRequestDAO request){
        CombinacionCCCResponseDTO response = new CombinacionCCCResponseDTO();
        BigDecimal OpResultado;
        String OpMSG;
        try {
            Map in = new HashMap<String,Object>();
            in.put("ip_cod_cia",request.getCodCia());
            in.put("ip_cod_secc",request.getCodSecc());
            in.put("ip_cod_producto",request.getCodProd());
            in.put("ip_cod_cob",request.getCodCob());
            in.put("ip_cod_causa",request.getCodCausa());
            in.put("ip_cod_cons",request.getCodConsecuencia());
            in.put("ip_idparammae",request.getIdParamMae());
            in.put("ip_simulacion",request.getSimulacion());

            Map out = this.execute(in);

            OpResultado = (BigDecimal)out.get("Op_Resultado");
            OpMSG= out.get("Op_MSG").toString();

            response.setOp_Resultado(OpResultado);
            response.setOp_MSG(OpMSG);

        } catch (Exception e) {
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getCause().getMessage());

        }
        return response;
    }


}
