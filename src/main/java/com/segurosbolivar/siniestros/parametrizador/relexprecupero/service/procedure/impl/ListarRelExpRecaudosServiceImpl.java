package com.segurosbolivar.siniestros.parametrizador.relexprecupero.service.procedure.impl;

import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DAO.RelExpRecuperoRequest;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.entity.DTO.RelExpRecuperoResponse;
import com.segurosbolivar.siniestros.parametrizador.relexprecupero.service.procedure.ListarRelExpRecaudosServiceInterface;
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
public class ListarRelExpRecaudosServiceImpl extends StoredProcedure implements ListarRelExpRecaudosServiceInterface {

    @Autowired
    DataSource dataSource;

    public ListarRelExpRecaudosServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_ListarRelExpRecupero");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codSecc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codProd", Types.INTEGER));
        declareParameter(new SqlOutParameter("op_curRel", Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public RelExpRecuperoResponse ListarRelExp(RelExpRecuperoRequest request){

        RelExpRecuperoResponse response = new RelExpRecuperoResponse();
        Map<String, Object> in = new HashMap<>();
        try{

            in.put("ip_codCia", request.getCodCia());
            in.put("ip_codSecc", request.getCodSecc());
            in.put("ip_codProd", request.getCodProd());


            Map<String, Object> out = this.execute(in);

            response.setOp_curRel(out.get("op_curRel"));
            response.setOp_Resultado((BigDecimal)out.get("Op_Resultado"));
            response.setOp_MSG(out.get("Op_MSG").toString());

        }
        catch(Exception e){
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getMessage());
        }
        return response;
    }
}
