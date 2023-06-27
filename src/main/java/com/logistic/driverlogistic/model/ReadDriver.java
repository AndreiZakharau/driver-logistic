package com.logistic.driverlogistic.model;

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
public final class ReadDriver {

  private Long id;
  private String categories;
  private String fullName;
  private String passportNumber;
  private ReadAccount driverAccount;
  private Byte experience;
  private String birthDay;

}
