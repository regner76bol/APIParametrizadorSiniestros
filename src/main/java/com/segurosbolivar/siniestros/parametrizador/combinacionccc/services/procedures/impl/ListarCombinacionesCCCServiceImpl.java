package com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DAO.CombinacionesRequestDAO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DTO.CombinacionCCCResponseDTO;
import com.segurosbolivar.siniestros.parametrizador.combinacionccc.services.procedures.ListarCombinacionesCCCServiceInterface;
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
public class ListarCombinacionesCCCServiceImpl extends StoredProcedure implements ListarCombinacionesCCCServiceInterface {

    @Autowired
    DataSource dataSource;

    public ListarCombinacionesCCCServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_ListarCombinacionsCCC");
        declareParameter(new SqlParameter("ip_codCia", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codSecc", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codProd", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codCob", Types.INTEGER));
        declareParameter(new SqlOutParameter("op_curCombCCC", Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public CombinacionCCCResponseDTO ListarCombCCC(CombinacionesRequestDAO request){
        CombinacionCCCResponseDTO response = new CombinacionCCCResponseDTO();
        Object opCurComb;
        BigDecimal OpResultado;
        String OpMSG;
        Map<String,Object> in = new HashMap<>();
        try {
            in.put("ip_codCia",request.getCodCia());
            in.put("ip_codSecc",request.getCodSecc());
            in.put("ip_codProd",request.getCodProd());
            in.put("ip_codCob",request.getCodCob());

            Map out = this.execute(in);
            opCurComb = out.get("op_curCombCCC");
            OpResultado = (BigDecimal) out.get("Op_Resultado");
            OpMSG = out.get("Op_MSG").toString();

            response.setOpcurCombCCC(opCurComb);
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
