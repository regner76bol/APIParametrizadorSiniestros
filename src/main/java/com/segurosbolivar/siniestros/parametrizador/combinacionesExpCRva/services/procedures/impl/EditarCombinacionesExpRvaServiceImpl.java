package com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DAO.CombinacionExpCRvaRequest;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DTO.CombinacionExpCRvaResponse;
import com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.services.procedures.EditarCombinacionesExpRvaServiceInterface;
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
public class EditarCombinacionesExpRvaServiceImpl extends StoredProcedure implements EditarCombinacionesExpRvaServiceInterface {

    @Autowired
    DataSource dataSource;

    public EditarCombinacionesExpRvaServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.EDITAR_COMBEXPRVA");
        declareParameter(new SqlParameter("ip_codCob", Types.INTEGER));
        declareParameter(new SqlParameter("ip_codCausa", Types.INTEGER));
        declareParameter(new SqlParameter("ip_tipoExped", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_codConcepRva", Types.INTEGER));
        declareParameter(new SqlParameter("ip_idA7000100", Types.INTEGER));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));

    }

    @Override
    public CombinacionExpCRvaResponse EditarCombinacionExpRva(CombinacionExpCRvaRequest request){
        CombinacionExpCRvaResponse response = new CombinacionExpCRvaResponse();
        BigDecimal OpResultado;
        String OpMSG;
        Map<String, Object> in = new HashMap<>();
        try {
            in.put("ip_codCob",request.getCodCob());
            in.put("ip_codCausa",request.getCodCausa());
            in.put("ip_tipoExped",request.getTipoExped());
            in.put("ip_codConcepRva",request.getCodConcepRva());
            in.put("ip_idA7000100",request.getIdA7000100());

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
