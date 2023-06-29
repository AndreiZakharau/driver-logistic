package com.logistic.driverlogistic.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Schema(description = "Model for update balance")
public class ChangeBalanceModel {

  @NotNull(message = "Value 'cash' cannot be null")
  private BigDecimal cash;

  @NotNull(message = "Value 'currency' cannot be null")
  @Size(min = 1, max = 3, message = "Value 'categories' size must be between 1 and 3")
  @Pattern(regexp = "[A-Z]*", message = "Value 'currency' must use only capital latin letters")
  private String code;

  @NotNull(message = "Value 'rates' cannot be null")
  private List<ExchangeRates> rates;

  @NotNull
  @Pattern(regexp = "[+-]", message = "Value 'operation must' be only plus(+) or mines(-)")
  private String operation;
}
