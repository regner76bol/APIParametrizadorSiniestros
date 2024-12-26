package com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.impl;

import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.Impl.CrearCausasCiaServiceImpl;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DTO.ConsecuenciasResponse;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.services.procedures.CrearConsecuenciaCiaServiceInterface;
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
public class CrearConsecuenciaCiaServiceImpl extends StoredProcedure implements CrearConsecuenciaCiaServiceInterface {

@Autowired
DataSource dataSource;

  public CrearConsecuenciaCiaServiceImpl(DataSource dataSource){
      super(dataSource,"PKG_PARAMETROS_SINIESTRO.CREAR_CONSXCIA");
      declareParameter(new SqlParameter("ip_cod_cia", Types.INTEGER));
      declareParameter(new SqlParameter("ip_cod_cons", Types.INTEGER));
      declareParameter(new SqlParameter("ip_consecuencia", Types.VARCHAR));
      declareParameter(new SqlParameter("ip_idparammae", Types.INTEGER));
      declareParameter(new SqlParameter("ip_simulacion", Types.INTEGER));
      declareParameter(new SqlOutParameter("Op_Resultado",Types.NUMERIC));
      declareParameter(new SqlOutParameter("Op_MSG",Types.VARCHAR));
  }

  public ConsecuenciasResponse execute(ConsecuenciasRequest request){

      Map in = new HashMap<String,Object>();
      ConsecuenciasResponse response = new ConsecuenciasResponse();

      try {


          in.put("ip_cod_cia", request.getCodCia());
          in.put("ip_cod_cons", request.getCodCons());
          in.put("ip_consecuencia", request.getConsecuencia());
          in.put("ip_idparammae", request.getIdParamMae());
          in.put("ip_simulacion", request.getSimulacion());

          Map out = this.execute(in);

          response.setOp_Resultado((BigDecimal) out.get("Op_Resultado"));
          response.setOp_MSG(out.get("Op_MSG").toString());

      } catch (Exception e) {
          response.setOp_Resultado(BigDecimal.valueOf(-1));
          response.setOp_MSG(e.getCause().getMessage());
      }
      return response;
  }
}