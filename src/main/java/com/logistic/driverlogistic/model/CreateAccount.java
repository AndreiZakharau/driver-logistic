package com.logistic.driverlogistic.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Model create account")
public class CreateAccount {

  @NotNull(message = "Value 'cash' cannot be null")
  private BigDecimal cash;

  @NotNull(message = "Value 'currency' cannot be null")
  private ReadCurrency currency;

}
