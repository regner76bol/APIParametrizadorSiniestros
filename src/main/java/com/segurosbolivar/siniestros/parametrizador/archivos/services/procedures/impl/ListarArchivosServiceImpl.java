package com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.ListarArchivosResponse;
import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.ListarArchivosServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Service
public class ListarArchivosServiceImpl extends StoredProcedure implements ListarArchivosServiceInterface {
    @Autowired
    DataSource dataSource;

    public ListarArchivosServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_ListarArchivos");
        declareParameter(new SqlOutParameter("Op_curArchivos",Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG",Types.VARCHAR));
    }

    @Override
    public ListarArchivosResponse execute(){
      Object Op_curArchivos;
      BigDecimal Op_Resultado;
      String Op_MSG="";
      ListarArchivosResponse lista = new ListarArchivosResponse();
      try {
          Map In = new HashMap<String, Object>();
          Map Out = this.execute(In);

          Op_curArchivos = Out.get("Op_curArchivos");
          Op_Resultado = (BigDecimal) Out.get("Op_Resultado");
          Op_MSG = Out.get("Op_MSG").toString();

          lista.setOp_curArchivos(Op_curArchivos);
          lista.setOp_Resultado(Op_Resultado);
          lista.setOp_MSG(Op_MSG);
      }
      catch (Exception e){
          lista.setOp_Resultado(BigDecimal.valueOf(-1));
          lista.setOp_MSG(e.getCause().getMessage());
      }
      return lista;
    }

}
