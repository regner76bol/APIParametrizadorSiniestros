package com.segurosbolivar.siniestros.parametrizador.causas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.segurosbolivar.siniestros.funciones.funcionesInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.CausasResponse;
import com.segurosbolivar.siniestros.parametrizador.causas.entity.DTO.ListarCausasResponse;
import com.segurosbolivar.siniestros.parametrizador.causas.service.business.BorrarCausaBusinessInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.service.business.CrearCausasBusinessInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.service.business.EditarCausaBusinessInterface;
import com.segurosbolivar.siniestros.parametrizador.causas.service.procedures.ListarCausasServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "*")
public class CausasController {

    @Autowired
    CrearCausasBusinessInterface negocio;

    @Autowired
    EditarCausaBusinessInterface negocioEditar;

    @Autowired
    BorrarCausaBusinessInterface negocioBorrar;

    @Autowired
    ListarCausasServiceInterface IListar;

    @Autowired
    funcionesInterface IFunciones;

     @PostMapping("/parametrizador/causas/crear")
    public ResponseEntity<CausasResponse> CrearCausas(@RequestBody CausasRequest request){
         ResponseEntity<CausasResponse> response= null;
         CausasResponse causa = negocio.CrearCausa(request);
         if (causa.getOp_Resultado()== BigDecimal.valueOf(0)) {
           response= new ResponseEntity<CausasResponse>(causa, HttpStatus.OK);
         } else if (causa.getOp_Resultado()== BigDecimal.valueOf(-1)) {
             response= new ResponseEntity<CausasResponse>(causa, HttpStatus.OK);
         }
         else {
             response= new ResponseEntity<CausasResponse>(causa, HttpStatus.INTERNAL_SERVER_ERROR);

         }

        return response;
     }

     @PostMapping("/parametrizador/causas/editar")
     public ResponseEntity<CausasResponse> postEditarCausa(@RequestBody CausasRequest request) throws JsonProcessingException{
         ResponseEntity<CausasResponse> response = null;
         CausasResponse editar = negocioEditar.EditarCausa(request);
         if (editar.getOp_Resultado()== BigDecimal.valueOf(0)) {
             response= new ResponseEntity<CausasResponse>(editar, HttpStatus.OK);
         } else if (editar.getOp_Resultado()== BigDecimal.valueOf(-1)) {
             response= new ResponseEntity<CausasResponse>(editar, HttpStatus.OK);
         }
         else {
             response= new ResponseEntity<CausasResponse>(editar, HttpStatus.INTERNAL_SERVER_ERROR);
         }

         return response;
     }

     @PostMapping("/parametrizador/causas/borrar")
     public ResponseEntity<CausasResponse> postBorrarCausa(@RequestBody CausasRequest request) throws JsonProcessingException{
         ResponseEntity<CausasResponse> response = null;
         CausasResponse borrar = negocioBorrar.BorrarCausa(request);
         if (borrar.getOp_Resultado()== BigDecimal.valueOf(0)) {
             response= new ResponseEntity<CausasResponse>(borrar, HttpStatus.OK);
         } else if (borrar.getOp_Resultado()== BigDecimal.valueOf(-1)) {
             response= new ResponseEntity<CausasResponse>(borrar, HttpStatus.OK);
         }
         else {
             response= new ResponseEntity<CausasResponse>(borrar, HttpStatus.INTERNAL_SERVER_ERROR);
         }

         return response;
     }


     @PostMapping("/parametrizador/causas/listar")
    public ResponseEntity<ListarCausasResponse> ListarCausas(@RequestBody CausasRequest request)throws JsonProcessingException {

         /*ObjectMapper objMapper = new ObjectMapper();
         JsonNode jsonNode = objMapper.readTree(param);
         String des = jsonNode.get("enc").asText();
        String desencriptado = null;
         try {
             Utilities.Encriptador desencrypt = new Utilities.Encriptador();
             desencriptado = desencrypt.decryptData(des);


         } catch (Exception e) {
             String x = e.getCause().getMessage();
         }

         ObjectMapper objectMapper = new ObjectMapper();
         RequestBase request = objectMapper.readValue(des, RequestBase.class);
*/
         ResponseEntity<ListarCausasResponse> response;
         ListarCausasResponse Listar = IListar.ListarCausas(request);
         if (Listar.getOp_Resultado()== BigDecimal.valueOf(0)) {
             response= new ResponseEntity<ListarCausasResponse>(Listar, HttpStatus.OK);
         } else if (Listar.getOp_Resultado()== BigDecimal.valueOf(-1)) {
             response= new ResponseEntity<ListarCausasResponse>(Listar, HttpStatus.OK);
         }
         else {
             response= new ResponseEntity<ListarCausasResponse>(Listar, HttpStatus.INTERNAL_SERVER_ERROR);
         }

         return response;
     }

     @PostMapping("/parametrizador/causas/buscarcodigo")
    public ResponseEntity<Integer> getBuscarCodigoCausa(@RequestBody CausasRequest request) throws  JsonProcessingException{
         ResponseEntity<Integer> response;
         Integer codCausa = IFunciones.Fn_BuscarCodigoCausa(request);
         if (codCausa > 0){
             response = new ResponseEntity<Integer>(codCausa,HttpStatus.OK);
         }
         else if(codCausa==0) {
             response = new ResponseEntity<Integer>(codCausa,HttpStatus.OK);
         }
         else {
             response = new ResponseEntity<Integer>(codCausa,HttpStatus.INTERNAL_SERVER_ERROR);
         }
         return response;
     }

}
