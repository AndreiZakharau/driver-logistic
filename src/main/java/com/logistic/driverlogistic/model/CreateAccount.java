package com.logistic.driverlogistic.model;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccount {

  @NotNull(message = "Value 'cash' cannot be null")
  private BigDecimal cash;
  private ReadCurrency currency;

}
