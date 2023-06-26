package com.logistic.driverlogistic.mapper;

import static com.logistic.driverlogistic.mapper.CurrencyMapper.CURRENCY_MAPPER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.logistic.driverlogistic.domain.Currency;
import com.logistic.driverlogistic.model.ReadCurrency;
import com.logistic.driverlogistic.testobject.ModelObject;
import org.junit.jupiter.api.Test;

class CurrencyMapperTest {

  @Test
  void currencyFromReadCurrency() {

    ReadCurrency readCurrency = ModelObject.getReadCurrency();

    Currency currency = CURRENCY_MAPPER.currencyFromReadCurrency(readCurrency);

    assertNotNull(currency);
    assertEquals(currency.getCode(), readCurrency.getCode());
    assertEquals(currency.getCountry(), readCurrency.getCountry());
  }

  @Test
  void readCurrencyFromCurrency() {

    Currency currency = ModelObject.getCurrency();

    ReadCurrency readCurrency = CURRENCY_MAPPER.readCurrencyFromCurrency(currency);

    assertNotNull(readCurrency);
    assertEquals(readCurrency.getCode(), currency.getCode());
    assertEquals(readCurrency.getCountry(), currency.getCountry());
  }
}