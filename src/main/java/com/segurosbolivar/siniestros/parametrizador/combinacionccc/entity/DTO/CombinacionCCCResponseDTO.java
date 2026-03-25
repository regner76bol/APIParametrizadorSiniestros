package com.segurosbolivar.siniestros.parametrizador.combinacionccc.entity.DTO;

import com.segurosbolivar.siniestros.commons.ResponseBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor

public class CombinacionCCCResponseDTO extends ResponseBase {
  Object opcurCombCCC;

  public CombinacionCCCResponseDTO(Object opcurCombCCC){
      this.opcurCombCCC=opcurCombCCC;
  }

}
