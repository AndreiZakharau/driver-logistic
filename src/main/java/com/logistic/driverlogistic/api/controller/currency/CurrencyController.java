package com.logistic.driverlogistic.api.controller.currency;

import com.logistic.driverlogistic.model.CreateCurrency;
import com.logistic.driverlogistic.model.ReadCurrency;
import com.logistic.driverlogistic.service.impl.CurrencyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyController implements CurrencyApi {

  private final CurrencyServiceImpl service;

  @Override
  public ReadCurrency addCurrency(CreateCurrency createCurrency) {
    return service.add(createCurrency);
  }

  @Override
  public void deleteCurrency(Long id) {

    service.delete(id);
  }

  @Override
  public ReadCurrency updateCurrency(CreateCurrency createCurrency, Long id) {

    return service.update(createCurrency, id);
  }

  @Override
  public ReadCurrency getCurrencyById(Long id) {

    return service.get(id);
  }

  @Override
  public Page<ReadCurrency> findAllCurrency(Integer page, Integer size) {

    return service.getAll(page, size);
  }
}
