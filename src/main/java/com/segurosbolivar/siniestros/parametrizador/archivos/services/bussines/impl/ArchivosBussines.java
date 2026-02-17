package com.segurosbolivar.siniestros.parametrizador.archivos.services.bussines.impl;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import com.segurosbolivar.siniestros.funciones.funcionesInterface;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DAO.ArchivosRequest;
import com.segurosbolivar.siniestros.parametrizador.archivos.entity.DTO.ArchivosResponse;
import com.segurosbolivar.siniestros.parametrizador.archivos.services.bussines.ArchivosBussinesInterface;
import com.segurosbolivar.siniestros.parametrizador.archivos.services.procedures.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;

@Service
public class ArchivosBussines implements ArchivosBussinesInterface {

    @Autowired
    funcionesInterface IFunciones;

    @Autowired
    UploadArchivosServiceInterface ICargarArchivos;

    @Autowired
    InsertarArchivosServiceImplInterface IInsertarArchivo;

    @Autowired
    InsertarTablaMaestraServiceImplInterface IInsertarMaestra;

    @Autowired
    ActualizarMaestraServiceInterface IActualizarMAestra;

    @Autowired
    ParametrizarSiniestrosServiceInterface IParametrizar;

    @Override
    public ResponseBase CargarArchivo(Path filePath, MultipartFile file, ArchivosRequest request) throws IOException {

        ArchivosResponse responseArch;
        ResponseBase resBase;
        ResponseBase response= new ResponseBase();

        //guarda el archivo en ubicacion fisica
        String path = ICargarArchivos.store(file);
        String fullNomArch= filePath +"\\" + file.getOriginalFilename();
        //crear registro en la tabla de archivos
        resBase = IInsertarArchivo.InsertarArchivo(request);
        if (resBase.getOp_Resultado().equals(BigDecimal.ZERO)){
            //crear archivo de control
            String ctl =  filePath.toString();
            ctl += IFunciones.CrearArchivoCTL(file.getOriginalFilename(),"1");

            if(!ctl.contains("Error:")){
                //ejecuar comando sqlloader para cargar la info del archivo en la tabla
                resBase = null;
                resBase = IFunciones.EjecuarSqlloader(ctl,fullNomArch);
                if (resBase.getOp_Resultado().equals(BigDecimal.ZERO)){
                    //Insertar registro en la tabla maestra
                    responseArch = IInsertarMaestra.InsertarTablaMaestra(request);
                    if (response.getOp_Resultado().equals(BigDecimal.ZERO)){
                        //Actualizar Maestra
                        resBase= null;
                        resBase =IActualizarMAestra.ActualizarMaestra(responseArch.getSecMae());
                        if (resBase.getOp_Resultado().equals(BigDecimal.ZERO)){
                            //Ejecutar procedimiento para crear la parametrizacion
                            resBase = IParametrizar.execute();
                            if (resBase.getOp_Resultado().equals(BigDecimal.ZERO)){
                                response.setOp_Resultado(resBase.getOp_Resultado());
                                response.setOp_MSG(resBase.getOp_MSG());
                            }
                            else{
                                response.setOp_Resultado(resBase.getOp_Resultado());
                                response.setOp_MSG(resBase.getOp_MSG());
                            }
                        }
                        else{
                            response.setOp_Resultado(resBase.getOp_Resultado());
                            response.setOp_MSG(resBase.getOp_MSG());
                        }
                    }
                    else{
                        response.setOp_Resultado(resBase.getOp_Resultado());
                        response.setOp_MSG(resBase.getOp_MSG());
                    }
                }
                else {
                    response.setOp_Resultado(resBase.getOp_Resultado());
                    response.setOp_MSG(resBase.getOp_MSG());
                }
            }
            else{
                response.setOp_Resultado(BigDecimal.valueOf(-1));
                response.setOp_MSG(ctl);
            }
        }
        else {
            response.setOp_Resultado(resBase.getOp_Resultado());
            response.setOp_MSG(resBase.getOp_MSG());
        }
      return response;
    }
}
