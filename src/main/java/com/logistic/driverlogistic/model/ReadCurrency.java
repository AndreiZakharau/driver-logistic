package com.logistic.driverlogistic.model;

import jakarta.validation.constraints.NotNull;
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
public class ReadCurrency {

  @NotNull(message = "Value code cannot be null")
  private String code;

  @NotNull(message = "Value country cannot be null")
  private String country;


}
