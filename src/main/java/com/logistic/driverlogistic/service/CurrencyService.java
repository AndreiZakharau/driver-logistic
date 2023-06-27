package com.logistic.driverlogistic.service;

import com.logistic.driverlogistic.model.CreateCurrency;
import com.logistic.driverlogistic.model.ReadCurrency;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface CurrencyService extends DomainService<CreateCurrency, ReadCurrency> {


  List<String> getListCode();
}
