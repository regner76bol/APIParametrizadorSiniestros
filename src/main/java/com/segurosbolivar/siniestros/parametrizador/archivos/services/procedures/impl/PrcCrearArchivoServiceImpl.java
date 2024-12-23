package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.CrearArchivosServiceInterface;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.ArchivosRequest;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.ArchivosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Service
public class PrcCrearArchivoServiceImpl extends StoredProcedure implements CrearArchivosServiceInterface {
@Autowired
    DataSource dataSource;

public PrcCrearArchivoServiceImpl(DataSource dataSource){
super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_CrearArchivo");
    declareParameter(new SqlParameter("ip_codCia", Types.NUMERIC));
    declareParameter(new SqlParameter("ip_codSecc", Types.NUMERIC));
    declareParameter(new SqlParameter("ip_codProducto", Types.NUMERIC));
    declareParameter(new SqlParameter("ip_nombreArchivo", Types.VARCHAR));
    declareParameter(new SqlParameter("ip_rutaFisica", Types.VARCHAR));
    declareParameter(new SqlParameter("ip_tipo", Types.NUMERIC));
    declareParameter(new SqlOutParameter("Op_Resultado",Types.NUMERIC));
    declareParameter(new SqlOutParameter("Op_MSG",Types.VARCHAR));
}

@Override
public ArchivosResponse execute(ArchivosRequest request){
Integer Op_Resultado=0;
String Op_MSG ="";
ArchivosResponse response = new ArchivosResponse();
try{
Map In = new HashMap<String,Object>();
    In.put("ip_codCia",request.getCodCia());
    In.put("ip_codSecc",request.getCodSecc());
    In.put("ip_codProducto",request.getCodProd());
    In.put("ip_nombreArchivo",request.getNombreArchivo());
    In.put("ip_rutaFisica",request.getRutaFisica());
    In.put("ip_tipo",request.getTipo());

    Map Out = this.execute(In);
    Op_Resultado =(Integer) Out.get("Op_Resultado");
    Op_MSG = Out.get("Op_MSG").toString();

    response.setOp_Resultado(Op_Resultado);
    response.setOp_MSG(Op_MSG);

}
catch (Exception e){
    response.setOp_Resultado(-1);
    response.setOp_MSG(e.getCause().getMessage());
}
return response;
}

}
