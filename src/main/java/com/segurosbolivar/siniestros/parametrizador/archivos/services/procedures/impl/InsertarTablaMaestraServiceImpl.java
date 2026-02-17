package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.impl;


import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.ArchivosRequest;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.ArchivosResponse;
import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.InsertarTablaMaestraServiceImplInterface;
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
public class InsertarTablaMaestraServiceImpl extends StoredProcedure implements InsertarTablaMaestraServiceImplInterface {

    @Autowired
    DataSource dataSource;

    public InsertarTablaMaestraServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_CrearMasivoMaestro");
        declareParameter(new SqlParameter("ip_codCia", Types.NUMERIC));
        declareParameter(new SqlParameter("ip_codSecc", Types.NUMERIC));
        declareParameter(new SqlParameter("ip_codProducto", Types.NUMERIC));
        declareParameter(new SqlParameter("ip_codAgrupacion", Types.VARCHAR));
        declareParameter(new SqlParameter("ip_idArchivo", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_SecMae", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public ArchivosResponse InsertarTablaMaestra(ArchivosRequest request){

        ArchivosResponse response = new ArchivosResponse();
        try{
            Map<String,Object> in = new HashMap<>();
            in.put("ip_codCia", request.getCodCia());
            in.put("ip_codSecc", request.getCodSecc());
            in.put("ip_codProducto", request.getCodProd());
            in.put("ip_codAgrupacion", request.getCodAgrupacion());
            in.put("ip_idArchivo", request.getIdArchivo());

            Map<String, Object> out = this.execute(in);

            response.setOp_MSG(out.get("Op_MSG").toString());
            response.setOp_Resultado((BigDecimal) out.get("Op_Resultado"));
            response.setSecMae((BigDecimal) out.get("Op_SecMae"));
        }
        catch (Exception e){
            response.setOp_Resultado(BigDecimal.valueOf(-1));
            response.setOp_MSG(e.getMessage());
            response.setSecMae(BigDecimal.valueOf(-1));

        }
        return response;
    }

}
