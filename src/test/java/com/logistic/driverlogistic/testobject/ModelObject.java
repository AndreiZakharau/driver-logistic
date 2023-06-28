package com.logistic.driverlogistic.testobject;

import com.logistic.driverlogistic.domain.Account;
import com.logistic.driverlogistic.domain.Currency;
import com.logistic.driverlogistic.domain.Driver;
import com.logistic.driverlogistic.model.CreateAccount;
import com.logistic.driverlogistic.model.CreateCurrency;
import com.logistic.driverlogistic.model.CreateDriver;
import com.logistic.driverlogistic.model.ReadAccount;
import com.logistic.driverlogistic.model.ReadCurrency;
import com.logistic.driverlogistic.model.ReadDriver;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ModelObject {

  public static Currency getCurrency() {

    return Currency.builder()
        .id(1L)
        .code("RUS")
        .country("Russia")
        .build();
  }

  public static Account getAccount() {

    return Account.builder()
        .id(1L)
        .cash(BigDecimal.valueOf(20.1))
        .currency(Currency.builder().id(1L).code("RUS").country("Russia").build())
        .driver(Driver.builder().id(1L).build())
        .build();
  }

  public static Driver getDriver() {

    return Driver.builder()
        .id(1L)
        .fullName("Ivan Ivanov")
        .passportNumber("CK9896625B89")
        .experience((byte) 3)
        .birthDay(LocalDate.ofEpochDay(2012 - 03 - 22))
        .categories("B,C")
        .account(Account.builder().id(1L).build()).build();
  }

  public static ReadDriver getReadDriver() {

    return ReadDriver.builder()
        .id(1L)
        .categories("B,C")
        .fullName("Ivan Ivanov")
        .passportNumber("CK9896625B89")
        .experience((byte) 3)
        .birthDay("2012-03-22")
        .driverAccount(ReadAccount.builder().cash(BigDecimal.valueOf(20.1)).build())
        .build();
  }

  public static CreateDriver getCreateDriver() {

    return CreateDriver.builder()
        .categories("B,C")
        .fullName("Ivan Ivanov")
        .passportNumber("CK9896625B89")
        .experience((byte) 3)
        .birthDay("2012-03-22")
        .account(getCreateAccount())
        .build();
  }

  public static ReadCurrency getReadCurrency() {

    return ReadCurrency.builder()
        .id(1L)
        .code("RUS")
        .country("Russia").build();
  }

  public static CreateAccount getCreateAccount() {

    return CreateAccount.builder()
        .cash(BigDecimal.valueOf(20.1))
        .currency(ReadCurrency.builder().id(1L).code("RUS").country("Russia").build())
        .build();
  }

  public static ReadAccount getReadAccount() {

    return ReadAccount.builder()
        .cash(BigDecimal.valueOf(20.1))
        .currency(getReadCurrency())
        .driverId(ReadDriver.builder().id(1L).build())
        .build();
  }

  public static CreateCurrency getCreateCurrency() {
    return CreateCurrency.builder()
        .code("RUS")
        .country("Russia")
        .build();
  }

  public static List<Currency> getListCurrency() {

    Currency rus = Currency.builder().id(1L).code("RUS").country("Russia").build();
    Currency eur = Currency.builder().id(2L).code("EUR").country("Italy").build();
    Currency usd = Currency.builder().id(3L).code("USD").country("USA").build();
    return List.of(rus, eur, usd);
  }
}

