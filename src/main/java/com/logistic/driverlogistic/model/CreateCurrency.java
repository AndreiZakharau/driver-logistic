package com.logistic.driverlogistic.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class CreateCurrency {

  @NotNull(message = "Value code cannot be null")
  @Size(min = 1, max = 3, message = "Value 'code' size must be between 1 and 3")
  @Pattern(regexp = "[A-Z]*", message = "Value 'code' must use only capital latin letters")
  private String code;

  @NotNull(message = "Value country cannot be null")
  @Size(min = 1, max = 50, message = "Value 'country' size must be between 1 and 50")
  private String country;

}
