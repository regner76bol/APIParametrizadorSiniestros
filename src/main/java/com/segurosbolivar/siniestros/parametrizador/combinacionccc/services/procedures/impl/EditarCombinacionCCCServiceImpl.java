package com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DAO.CombinacionesRequestDAO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DTO.CombinacionCCCResponseDTO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.procedures.EditarCombinacionCCCServiceInterface;
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
public class EditarCombinacionCCCServiceImpl extends StoredProcedure implements EditarCombinacionCCCServiceInterface {

    @Autowired
    DataSource dataSource;

    public EditarCombinacionCCCServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.EDITAR_COMBCCC");
        declareParameter(new SqlParameter("ip_cod_cob", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_causa", Types.INTEGER));
        declareParameter(new SqlParameter("ip_cod_cons", Types.INTEGER));
        declareParameter(new SqlParameter("ip_id_a7000100", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public CombinacionCCCResponseDTO EditarCombCCC(CombinacionesRequestDAO request){
        CombinacionCCCResponseDTO response = new CombinacionCCCResponseDTO();
        BigDecimal OpResultado;
        String OpMSG;
        Map<String,Object> in = new HashMap<>();
        try {
            in.put("ip_cod_cob", request.getCodCob());
            in.put("ip_cod_causa", request.getCodCausa());
            in.put("ip_cod_cons", request.getCodConsecuencia());
            in.put("ip_id_a7000100", request.getIdA7000100());

            Map out = this.execute(in);

            OpResultado= (BigDecimal) out.get("Op_Resultado");
            OpMSG= out.get("Op_MSG").toString();

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
