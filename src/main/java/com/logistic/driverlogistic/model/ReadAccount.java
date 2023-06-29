package com.logistic.driverlogistic.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Model view account")
public class ReadAccount {

  private BigDecimal cash;
  private ReadCurrency currency;
  private ReadDriver driverId;

}
