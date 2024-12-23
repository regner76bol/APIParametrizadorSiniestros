package com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DAO.CombinacionExpCRvaRequest;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DTO.CombinacionExpCRvaResponse;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.procedures.ListarCombinacionesExpRvaServiceInterface;
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
public class ListarCombinacionesExpRvaServiceImpl extends StoredProcedure implements ListarCombinacionesExpRvaServiceInterface {

    @Autowired
    DataSource dataSource;

    public ListarCombinacionesExpRvaServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_ListarCombinacionesEXRV");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codCob", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codCausa", Types.INTEGER));
        declareParameter(new SqlOutParameter("op_curCombEXRV", Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public CombinacionExpCRvaResponse ListarCombExpRva(CombinacionExpCRvaRequest request){

        CombinacionExpCRvaResponse response = new CombinacionExpCRvaResponse();
        Object opcurCombEXRV;
        BigDecimal OpResultado;
        String OpMSG;
        Map<String, Object> in = new HashMap<>();
        try {
            in.put("ip_codCia", request.getCodCia());
            in.put("ip_codCob", request.getCodCob());
            in.put("ip_codCausa", request.getCodCausa());

            Map out = this.execute(in);

            opcurCombEXRV = out.get("op_curCombEXRV");
            OpResultado= (BigDecimal) out.get("Op_Resultado");
            OpMSG=out.get("Op_MSG").toString();

            response.setOpcurCombEXRV(opcurCombEXRV);
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
