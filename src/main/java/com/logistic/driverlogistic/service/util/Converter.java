package com.logistic.driverlogistic.service.util;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class Converter {

  public BigDecimal returnCashAfterConvert(double rate, BigDecimal cash) {

    return cash.multiply(BigDecimal.valueOf(rate));
  }

}
