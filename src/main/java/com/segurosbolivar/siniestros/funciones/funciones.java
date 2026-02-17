package com.segurosbolivar.siniestros.funciones;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.*;
import java.io.IOException;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class funciones implements funcionesInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final Path filePath = Paths.get("C:\\Users\\79745463\\Documents\\Archivos\\");

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


    public String CrearArchivoCTL(String nombArch, String tipoArchivo)  throws IOException {
        String tabla = "";
        String campos = "";
        String ctl ="";
        switch (tipoArchivo)
        {
            case "1":
                tabla = "APPEND INTO TABLE SIM_PARSINI_MASIVO_DET";
                campos = "(ID_PARAMETRIZACION_MAE, COD_AGRUPACION, COD_CIA, COD_SECC, COD_PRODUCTO, COD_COB, COBERTURA, COD_CAUSA, CAUSA, COD_CONS, CONSECUENCIA, TIPO_EXPED, DESC_EXPED, COD_CONCEP_RVA, DESC_CONCEP, TITULO_DATO_VARIABLE, ID_TIPO_RESERVA, CODIGO_TIPO_RESERVA, VALOR_FIJO, DESC_TIPO_RESERVA, CLASE_EXPED, LIQUIDACION_AUTOMATICA, ID_BIEN, ID_ESTRATEGIA)";
                break;
            case "2":
                tabla = "APPEND INTO TABLE SIM_PARAM_SINI_DV";
                campos = "(ID_DV,ID_PARAMETRIZACION_MAE, COD_CIA, COD_SECC, COD_PRODUCTO, COD_NIVEL, NUM_SECU, COD_CAMPO, COD_REGLA, COD_USER, MCA_BAJA, ACEPTA_NULL, OBLIGATORIO, MCA_VISIBLE, TABLA_VAL, PGM_HELP, LISTA_VALORES, REG_PRE_FIELD, TEXTO_ERROR, VALOR_DEFECTO, OPERADOR, MCA_MULTIREG, MCA_COMMIT, CATEGORIA, ORDEN_CATEGORIA, TUPLA, COMPONENTE, TITULO, URL, COD_LISTA, LABEL1, VALOR1, LABEL2, VALOR2, LABEL3, VALOR3, FECHA_CREACION, FECHA_ALTA, USUARIO_CREACION, FECHA_MODIFICA, USUARIO_MODIFICA, ESTADO, OBLIG_AVISO, FORMATO, LISTA_DEPENDIENTE, HAB_CONSULTA, HACE_PRECAMPO, ID_ARCHIVO, ID_BIEN)";
                break;
            case "3":
                tabla = "APPEND INTO TABLE SIM_PARAM_SINI_CONCEPLIQ";
                campos = "(ID_CONCEPLIQ, ID_PARAMETRIZACION_MAE, ID_ARCHIVO, COD_CIA, COD_CONCEP_RVA, COD_CONCEP_LIQ, DESC_CONCEP_LI, COD_USER, MCA_BAJA, COD_RAMO, COD_SECC, COD_PAGO, TIPO_PAGO, SIM_FECHA_CREACION, SIM_USUARIO_CREACION, SIM_FECHA_MODIFICA, SIM_USUARIO_MODIFICA)";
                break;
            default:
                break;
        }

        String content = "";

        Path fileName = Paths.get(filePath+"\\"+nombArch+".ctl");
        try {
            if (!Files.exists(fileName)) {
                Files.createFile(fileName);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(fileName, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                writer.write(content);
                writer.newLine();
                content =("LOAD DATA");
                writer.write(content);
                writer.newLine();
                content =("CHARACTERSET UTF8");
                writer.write(content);
                writer.newLine();
                content =("INFILE " + filePath + nombArch + "'");
                writer.write(content);
                writer.newLine();
                content =("BADFILE " + filePath + nombArch + ".bad'");
                writer.write(content);
                writer.newLine();
                content =("DISCARDFILE" + filePath + nombArch + ".dsc'");
                writer.write(content);
                writer.newLine();
                content =(tabla);
                writer.write(content);
                writer.newLine();
                content =("FIELDS TERMINATED BY " + "\"" + ";" + "\"" + " TRAILING NULLCOLS");
                writer.write(content);
                writer.newLine();
                content =(campos);
                writer.write(content);
                writer.newLine();

            }
            ctl =fileName.getFileName().toString();
        }
        catch (Exception e){
            ctl = "Error: " + e.getMessage();
        }

        return  ctl;
    }

    public ResponseBase EjecuarSqlloader(String ctl, String data) throws IOException {
        ResponseBase res = new ResponseBase();
        try {
            // Para Windows, usar cmd.exe
            String[] cmd = {
                "cmd.exe", 
                "/c", 
                "sqlldr", 
                "userid=ops$puma/puma@DESA_TRONMASK",
                "control=" + ctl,
                "data=" + data,
                "log=" + data + ".log",
                "bad=" + data + ".bad",
                "discard=" + data + ".discard"
            };
            
            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            
            // Capturar salida completa
            java.util.Scanner s = new java.util.Scanner(process.getInputStream()).useDelimiter("\\A");
            String output = s.hasNext() ? s.next() : "";
            
            // Esperar a que termine el proceso
            int exitCode = process.waitFor();
            
            if (exitCode != 0) {
                System.err.println("SQL*Loader error: " + output);
                res.setOp_MSG("Error en SQL*Loader: código " + exitCode);
                res.setOp_Resultado(BigDecimal.valueOf(-1));
            } else {
                System.out.println("SQL*Loader ejecutado exitosamente");
                // Continuar con el resto del código
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            res.setOp_MSG("Error: " + e.getMessage());
            res.setOp_Resultado(BigDecimal.valueOf(-1));
        }
        return res;
    }


}
