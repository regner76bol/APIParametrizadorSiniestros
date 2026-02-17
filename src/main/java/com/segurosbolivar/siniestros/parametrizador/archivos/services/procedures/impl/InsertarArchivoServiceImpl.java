package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.impl;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.ArchivosRequest;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.ArchivosResponse;
import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.InsertarArchivosServiceImplInterface;
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
public class InsertarArchivoServiceImpl extends StoredProcedure implements InsertarArchivosServiceImplInterface {

    @Autowired
    DataSource dataSource;

    public InsertarArchivoServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_CrearArchivo");
        declareParameter(new SqlParameter("ip_codCia", Types.NUMERIC));
        declareParameter(new SqlParameter("ip_codSecc", Types.NUMERIC));
        declareParameter(new SqlParameter("ip_codProducto", Types.NUMERIC));
        declareParameter(new SqlParameter("ip_nombreArchivo", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_rutaFisica", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_tipo", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public ResponseBase InsertarArchivo(ArchivosRequest request){

        ResponseBase response = new ResponseBase();

        try {
            Map<String, Object> in = new HashMap<>();
            in.put("ip_codCia", request);
            in.put("ip_codSecc", request);
            in.put("ip_codProducto", request);
            in.put("ip_nombreArchivo", request);
            in.put("ip_rutaFisica", request);
            in.put("ip_tipo", request);

            Map<String, Object> out = this.execute(in);

            response.setOp_Resultado((BigDecimal) out.get("Op_Resultado"));
            response.setOp_MSG(out.get("Op_MSG").toString());

        }
        catch (Exception e){
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getMessage());
        }
        return response;
    }


}
