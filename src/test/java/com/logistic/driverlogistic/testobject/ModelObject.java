package com.logistic.driverlogistic.testobject;

import com.logistic.driverlogistic.domain.Account;
import com.logistic.driverlogistic.domain.Currency;
import com.logistic.driverlogistic.domain.Driver;
import com.logistic.driverlogistic.model.CreateAccount;
import com.logistic.driverlogistic.model.CreateDriver;
import com.logistic.driverlogistic.model.ReadAccount;
import com.logistic.driverlogistic.model.ReadCurrency;
import com.logistic.driverlogistic.model.ReadDriver;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ModelObject {

  public static Currency getCurrency() {

    return Currency.builder()
        .code("RUS")
        .country("Russia")
        .build();
  }

  public static Account getAccount() {

    return Account.builder()
        .id(3L)
        .cash(BigDecimal.valueOf(20.1))
        .currency(Currency.builder().code("RUS").build())
        .driverId(Driver.builder().id(1L).build())
        .build();
  }

  public static Driver getDriver() {

    return Driver.builder()
        .id(1L)
        .fullName("B,C")
        .fullName("Ivan Ivanov")
        .passportNumber("CK9896625B89")
        .experience((byte) 3)
        .birthDay(LocalDate.ofEpochDay(2012 - 03 - 22))
        .driverAccount(getAccount()).build();
  }

  public static ReadDriver getReadDriver() {

    return ReadDriver.builder()
        .id(1L)
        .fullName("B,C")
        .fullName("Ivan Ivanov")
        .passportNumber("CK9896625B89")
        .experience((byte) 3)
        .birthDay("2012-03-22")
        .build();
  }

  public static CreateDriver getCreateDriver() {

    return CreateDriver.builder()
        .fullName("B,C")
        .fullName("Ivan Ivanov")
        .passportNumber("CK9896625B89")
        .experience((byte) 3)
        .birthDay("2012-03-22")
        .build();
  }

  public static ReadCurrency getReadCurrency() {

    return ReadCurrency.builder()
        .code("RUS")
        .country("Russia").build();
  }

  public static CreateAccount getCreateAccount() {

    return CreateAccount.builder()
        .cash(BigDecimal.valueOf(20.1))
        .currency(Currency.builder().code("RUS").build())
        .driverId(Driver.builder().id(1L).build())
        .build();
  }


  public static ReadAccount getReadAccount() {

    return ReadAccount.builder()
        .id(3L)
        .cash(BigDecimal.valueOf(20.1))
        .currency(Currency.builder().code("RUS").build())
        .driverId(Driver.builder().id(1L).build())
        .build();
  }
}

