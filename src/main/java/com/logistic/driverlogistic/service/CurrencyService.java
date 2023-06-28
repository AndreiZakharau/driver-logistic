package com.logistic.driverlogistic.service;

import com.logistic.driverlogistic.model.CreateCurrency;
import com.logistic.driverlogistic.model.ReadCurrency;
import java.util.List;

public interface CurrencyService extends DomainService<CreateCurrency, ReadCurrency> {


  List<String> getListCode();

  ReadCurrency getCurrencyByCode(String code);
}
