package com.logistic.driverlogistic.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Model view driver")
public final class ReadDriver {

  private Long id;
  private String categories;
  private String fullName;
  private String passportNumber;
  private ReadAccount driverAccount;
  private Byte experience;
  private String birthDay;

}
