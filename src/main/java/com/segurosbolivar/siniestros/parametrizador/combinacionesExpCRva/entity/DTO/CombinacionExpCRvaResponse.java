package com.segurosbolivar.siniestros.parametrizador.combinacionesExpCRva.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CombinacionExpCRvaResponse extends ResponseBase {

    private Object opcurCombEXRV;
    public CombinacionExpCRvaResponse(Object opcurCombEXRV){
      this.opcurCombEXRV=opcurCombEXRV;
    }

}
