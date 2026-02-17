package com.segurosbolivar.siniestros.generales.services.procedures.impl;

import com.segurosbolivar.siniestros.generales.entity.DTO.GeneralesResponseDTO;
import com.segurosbolivar.siniestros.generales.services.procedures.CargarTipoReservaServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

@Service
public class CargarTipoReeservaServiceImpl extends StoredProcedure implements CargarTipoReservaServiceInterface {

    @Autowired
    DataSource dataSource;

    public CargarTipoReeservaServiceImpl(DataSource dataSource){
        super(dataSource,"PKG_PARAMETROS_SINIESTRO.Proc_CargarTipoReserva");
        declareParameter(new SqlOutParameter("Op_curTipoRva", Types.REF_CURSOR));
        declareParameter(new SqlOutParameter("Op_Resultado", Types.NUMERIC));
        declareParameter(new SqlOutParameter("Op_MSG", Types.VARCHAR));
    }

    @Override
    public GeneralesResponseDTO CargarTipoReserva(){
        GeneralesResponseDTO response = new GeneralesResponseDTO();
        Object opCurTipoRva;
        BigDecimal OpResultado;
        String OpMSG;
        //Map<String, Object> in = new HashMap<>();
        try {

            Map out = this.execute();
            opCurTipoRva = out.get("Op_curTipoRva");
            OpResultado= (BigDecimal) out.get("Op_Resultado");
            OpMSG = out.get("Op_MSG").toString();

            response.setOpcurTipoRva(opCurTipoRva);
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
