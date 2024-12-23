package com.segurosbolivar.siniestros.funciones;

import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import com.segurosbolivar.siniestros.parametrizador.rvaautomatica.entity.DAO.ReservaAutomaticaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;

import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class funciones implements funcionesInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer Fn_ExisteCausa(String codCia, String codSecc, String codProducto, String codCausa){
        String statement="{? = call PKG_PARAMETROS_SINIESTRO.FN_EXISTECAUSA1(?,?,?,?)}";

        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlOutParameter("result",Types.INTEGER));
        params.add(new SqlParameter("ip_cod_cia", Types.INTEGER));
        params.add(new SqlParameter("ip_cod_secc", Types.INTEGER));
        params.add(new SqlParameter("ip_cod_producto",Types.INTEGER));
        params.add(new SqlParameter("ip_cod_causa",Types.INTEGER));

        Map in = new HashMap<String,Object>();
        in.put("ip_cod_cia", codCia);
        in.put("ip_cod_secc", codSecc);
        in.put("ip_cod_producto",codProducto);
        in.put("ip_cod_causa",codCausa);

        CallableStatementCreatorFactory factory = new CallableStatementCreatorFactory(statement,params);
        CallableStatementCreator creator = factory.newCallableStatementCreator(in);

        Map<String,Object> result= jdbcTemplate.call(creator,params);
        Integer res =  (Integer) result.get("result");

        return res;
    }

    public Integer Fn_ExisteConsecuencia(String codCia, String codSecc, String codProducto, String codCons){
        String statement="{? = call PKG_PARAMETROS_SINIESTRO.FN_EXISTECONSECUENCIA1(?,?,?,?)}";

        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlOutParameter("result",Types.INTEGER));
        params.add(new SqlParameter("ip_cod_cia", Types.INTEGER));
        params.add(new SqlParameter("ip_cod_secc", Types.INTEGER));
        params.add(new SqlParameter("ip_cod_producto",Types.INTEGER));
        params.add(new SqlParameter("ip_cod_cons",Types.INTEGER));

        Map in = new HashMap<String,Object>();
        in.put("ip_cod_cia", codCia);
        in.put("ip_cod_secc", codSecc);
        in.put("ip_cod_producto",codProducto);
        in.put("ip_cod_cons",codCons);

        CallableStatementCreatorFactory factory = new CallableStatementCreatorFactory(statement,params);
        CallableStatementCreator creator = factory.newCallableStatementCreator(in);

        Map<String,Object> result= jdbcTemplate.call(creator,params);
        Integer res =  (Integer) result.get("result");

        return res;
    }


    public Integer Fn_ExisteCobertura(String codCia, String codProducto, String codCob){
        String statement="{? = call PKG_PARAMETROS_SINIESTRO.FN_EXISTECOBERTURA1(?,?,?)}";

        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlOutParameter("result",Types.INTEGER));
        params.add(new SqlParameter("ip_cod_cia", Types.INTEGER));
        params.add(new SqlParameter("ip_cod_producto",Types.INTEGER));
        params.add(new SqlParameter("ip_cod_cob",Types.INTEGER));

        Map in = new HashMap<String,Object>();
        in.put("ip_cod_cia", codCia);
        in.put("ip_cod_producto",codProducto);
        in.put("ip_cod_cob",codCob);

        CallableStatementCreatorFactory factory = new CallableStatementCreatorFactory(statement,params);
        CallableStatementCreator creator = factory.newCallableStatementCreator(in);

        Map<String,Object> result= jdbcTemplate.call(creator,params);
        Integer res =  (Integer) result.get("result");

        return res;
    }

    public Integer Fn_ExisteExpediente(String codCia, String codSecc, String codProducto, String tipoExped){
        String statement="{? = call PKG_PARAMETROS_SINIESTRO.FN_EXISTEEXPEDIENTE1(?,?,?,?)}";

        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlOutParameter("result",Types.INTEGER));
        params.add(new SqlParameter("ip_cod_cia", Types.INTEGER));
        params.add(new SqlParameter("ip_cod_secc", Types.INTEGER));
        params.add(new SqlParameter("ip_cod_producto",Types.INTEGER));
        params.add(new SqlParameter("ip_tipo_exped",Types.VARCHAR));

        Map in = new HashMap<String,Object>();
        in.put("ip_cod_cia", codCia);
        in.put("ip_cod_secc", codSecc);
        in.put("ip_cod_producto",codProducto);
        in.put("ip_tipo_exped",tipoExped);

        CallableStatementCreatorFactory factory = new CallableStatementCreatorFactory(statement,params);
        CallableStatementCreator creator = factory.newCallableStatementCreator(in);

        Map<String,Object> result= jdbcTemplate.call(creator,params);
        Integer res =  (Integer) result.get("result");

        return res;
    }

    public Integer Fn_ExisteConceptoRva(String codCia, String conRva){
        String statement="{? = call PKG_PARAMETROS_SINIESTRO.FN_EXISTECONCEPRVA1(?,?)}";

        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlOutParameter("result",Types.INTEGER));
        params.add(new SqlParameter("ip_cod_cia", Types.INTEGER));
        params.add(new SqlParameter("ip_cod_concep_rva",Types.INTEGER));

        Map in = new HashMap<String,Object>();
        in.put("ip_cod_cia", codCia);
        in.put("ip_cod_concep_rva",conRva);

        CallableStatementCreatorFactory factory = new CallableStatementCreatorFactory(statement,params);
        CallableStatementCreator creator = factory.newCallableStatementCreator(in);

        Map<String,Object> result= jdbcTemplate.call(creator,params);
        Integer res =  (Integer) result.get("result");

        return res;
    }

    public Integer Fn_ExisteTipoRva(String codCia, String tipoRva){
        String statement="{? = call PKG_PARAMETROS_SINIESTRO.FN_EXISTETIPORESERVA1(?)}";

        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlOutParameter("result",Types.INTEGER));
        params.add(new SqlParameter("ip_tipo_rva", Types.INTEGER));

        Map in = new HashMap<String,Object>();
        in.put("ip_cod_cia", codCia);
        in.put("ip_tipo_rva",tipoRva);

        CallableStatementCreatorFactory factory = new CallableStatementCreatorFactory(statement,params);
        CallableStatementCreator creator = factory.newCallableStatementCreator(in);

        Map<String,Object> result= jdbcTemplate.call(creator,params);
        Integer res =  (Integer) result.get("result");

        return res;
    }

    public Integer Fn_ExisteCombCCC(String codCia, String codSecc, String codProducto,String codCausa, String codCons){
        String statement="{? = call PKG_PARAMETROS_SINIESTRO.FN_EXISTECOMBCCC(?,?,?,?,?)}";

        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlOutParameter("result",Types.INTEGER));
        params.add(new SqlParameter("ip_codCia", Types.INTEGER));
        params.add(new SqlParameter("ip_codSecc", Types.INTEGER));
        params.add(new SqlParameter("ip_codProducto",Types.INTEGER));
        params.add(new SqlParameter("ip_codCausa",Types.INTEGER));
        params.add(new SqlParameter("ip_codCons",Types.INTEGER));
        Map in = new HashMap<String,Object>();
        in.put("ip_codCia", codCia);
        in.put("ip_codSecc", codSecc);
        in.put("ip_codProducto",codProducto);
        in.put("ip_codCausa",codCausa);
        in.put("ip_codCons",codCons);

        CallableStatementCreatorFactory factory = new CallableStatementCreatorFactory(statement,params);
        CallableStatementCreator creator = factory.newCallableStatementCreator(in);

        Map<String,Object> result= jdbcTemplate.call(creator,params);
        Integer res =  (Integer) result.get("result");

        return res;
    }

    public Integer Fn_ExisteCombExpCRva(String codCia, String codCob, String codCausa,String tipoExped, String conRva){
        String statement="{? = call PKG_PARAMETROS_SINIESTRO.FN_EXISTECOMBEXPCRVA(?,?,?,?,?)}";

        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlOutParameter("result",Types.INTEGER));
        params.add(new SqlParameter("ip_codCia", Types.INTEGER));
        params.add(new SqlParameter("ip_codCob", Types.INTEGER));
        params.add(new SqlParameter("ip_codCausa",Types.INTEGER));
        params.add(new SqlParameter("ip_tipoExped",Types.VARCHAR));
        params.add(new SqlParameter("ip_codConcepRva",Types.INTEGER));
        Map in = new HashMap<String,Object>();
        in.put("ip_codCia", codCia);
        in.put("ip_codCob", codCob);
        in.put("ip_codCausa",codCausa);
        in.put("ip_tipoExped",tipoExped);
        in.put("ip_codConcepRva",conRva);

        CallableStatementCreatorFactory factory = new CallableStatementCreatorFactory(statement,params);
        CallableStatementCreator creator = factory.newCallableStatementCreator(in);

        Map<String,Object> result= jdbcTemplate.call(creator,params);
        Integer res =  (Integer) result.get("result");

        return res;
    }

    public Integer Fn_BuscarCodigoCausa(CausasRequest request){
        String statement="{? = call PKG_PARAMETROS_SINIESTRO.FN_BUSCAR_CODCAUSA(?,?)}";
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlOutParameter("result",Types.INTEGER));
        params.add(new SqlParameter("ip_cod_cia", Types.INTEGER));
        params.add(new SqlParameter("ip_tipo_causa", Types.INTEGER));
        Map in = new HashMap<String,Object>();
        in.put("ip_cod_cia", request.getCodCia());
        in.put("ip_tipo_causa", request.getTipoCausa());
        CallableStatementCreatorFactory factory = new CallableStatementCreatorFactory(statement,params);
        CallableStatementCreator creator = factory.newCallableStatementCreator(in);

        Map<String,Object> result= jdbcTemplate.call(creator,params);
        Integer res =  (Integer) result.get("result");

        return res;
    }

    public Integer fn_BuscarCodigoConsecuencia(ConsecuenciasRequest request){
        String statement = "{? = call PKG_PARAMETROS_SINIESTRO.FN_BUSCAR_CONSECUENCIA(?)}";
        List<SqlParameter> params = new ArrayList<>();
        params.add(new SqlOutParameter("result",Types.INTEGER));
        params.add(new SqlParameter("ip_cod_cia", Types.INTEGER));
        Map in = new HashMap<String,Object>();
        in.put("ip_cod_cia", request.getCodCia());
        CallableStatementCreatorFactory factory = new CallableStatementCreatorFactory(statement,params);
        CallableStatementCreator creator = factory.newCallableStatementCreator(in);

        Map<String,Object> result= jdbcTemplate.call(creator,params);
        Integer res =  (Integer) result.get("result");

        return res;
    }


}
