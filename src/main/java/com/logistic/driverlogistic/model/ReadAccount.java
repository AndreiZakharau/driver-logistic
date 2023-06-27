package com.logistic.driverlogistic.model;

import com.logistic.driverlogistic.domain.Currency;
import com.logistic.driverlogistic.domain.Driver;
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
public class ReadAccount {

  private Long id;
  private BigDecimal cash;
  private ReadCurrency currency;
  private ReadDriver driverId;

}
