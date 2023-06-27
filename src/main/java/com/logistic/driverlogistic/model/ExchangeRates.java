package com.logistic.driverlogistic.model;

import com.logistic.driverlogistic.domain.Currency;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExchangeRates {

  @NotNull(message = "Value 'firstCode' cannot be null")
  @Size(min = 1, max = 3, message = "Value 'firstCode' size must be between 1 and 3")
  @Pattern(regexp = "[A-Z]*", message = "Value 'firstCode' must use only capital latin letters")
  ReadCurrency firstCode;

  @NotNull(message = "Value 'secondCode' cannot be null")
  @Size(min = 1, max = 3, message = "Value 'secondCode' size must be between 1 and 3")
  @Pattern(regexp = "[A-Z]*", message = "Value 'secondCode' must use only capital latin letters")
  ReadCurrency secondCode;

  @NotNull(message = "Value 'rate' cannot be null")
  @Pattern(regexp = "[1-9]\\d*?(,\\d{1,3})?", message = "Value 'rate' must use only capital latin letters")
  double rate;


}
