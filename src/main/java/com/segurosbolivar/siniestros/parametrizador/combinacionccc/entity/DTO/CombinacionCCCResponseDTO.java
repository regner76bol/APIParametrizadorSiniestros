package com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class CombinacionCCCResponseDTO extends ResponseBase {
  Object opcurCombCCC;

  public CombinacionCCCResponseDTO(Object opcurCombCCC){
      this.opcurCombCCC=opcurCombCCC;
  }

}
