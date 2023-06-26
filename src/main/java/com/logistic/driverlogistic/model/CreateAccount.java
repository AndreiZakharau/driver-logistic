package com.logistic.driverlogistic.model;

import com.logistic.driverlogistic.domain.Currency;
import com.logistic.driverlogistic.domain.Driver;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateAccount {

  @NotNull(message = "Value 'cash' cannot be null")
  private BigDecimal cash;
  @NotNull(message = "Value 'currency' cannot be null")
  @Size(min = 1, max = 3, message = "Value 'categories' size must be between 1 and 3")
  @Pattern(regexp = "A-Z", message = "Value 'currency' must use only capital latin letters")
  private Currency currency;
  @NotNull(message = "Value 'driverId' cannot be null")
  private Driver driverId;
}
