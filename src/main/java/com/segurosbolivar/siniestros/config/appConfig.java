package com.segurosbolivar.siniestros.config;

import com.segurosbolivar.siniestros.parametrizador.causas.entity.DAO.CausasRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class appConfig {
    @Bean
    public CausasRequest causasRequest(){
      return new CausasRequest();
    }
}
