package com.segurosbolivar.siniestros.funciones;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.consecuecias.entity.DAO.ConsecuenciasRequest;

import java.io.IOException;

public interface funcionesInterface {

    Integer Fn_ExisteCausa(String codCia, String codSecc, String codProducto, String codCausa);

    Integer Fn_ExisteConsecuencia(String codCia, String codSecc, String codProducto, String codCons);

    Integer Fn_ExisteCobertura(String codCia, String codProducto, String codCob);

    Integer Fn_ExisteExpediente(String codCia, String codSecc, String codProducto, String tipoExped);

    Integer Fn_ExisteConceptoRva(String codCia, String conRva);

    Integer Fn_ExisteTipoRva(String codCia, String tipoRva);

    Integer Fn_ExisteCombCCC(String codCia, String codSecc, String codProducto,String codCausa, String codCons);

    Integer Fn_ExisteCombExpCRva(String codCia, String codCob, String codCausa,String tipoExped, String conRva);

    Integer Fn_BuscarCodigoCausa(CausasRequest request);
    Integer fn_BuscarCodigoConsecuencia(ConsecuenciasRequest request);

    String CrearArchivoCTL(String nombArch, String tipoArchivo) throws IOException;

    ResponseBase EjecuarSqlloader(String ctl, String data) throws IOException;
}
