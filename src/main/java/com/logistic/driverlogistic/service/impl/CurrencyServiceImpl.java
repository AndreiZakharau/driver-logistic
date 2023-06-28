package com.logistic.driverlogistic.service.impl;

import com.logistic.driverlogistic.domain.Currency;
import com.logistic.driverlogistic.mapper.CurrencyMapper;
import com.logistic.driverlogistic.model.CreateCurrency;
import com.logistic.driverlogistic.model.ReadCurrency;
import com.logistic.driverlogistic.repository.CurrencyRepository;
import com.logistic.driverlogistic.service.CurrencyService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

  private final CurrencyRepository repository;

  private final CurrencyMapper mapper;

  @Transactional
  @Override
  public ReadCurrency add(CreateCurrency createCurrency) {

    return mapper.readCurrencyFromCurrency(
        repository.save(mapper.currencyFromCreateCurrency(createCurrency)));
  }

  @Override
  public void delete(long id) {

    currencyByIdIsPresent(id);
    repository.deleteById(id);
  }


  @Transactional
  @Override
  public ReadCurrency update(CreateCurrency createCurrency, long id) {

    currencyByIdIsPresent(id);
    Currency currency = mapper.currencyFromCreateCurrency(createCurrency);
    return mapper.readCurrencyFromCurrency(
        repository.save(currency));

  }

  @Override
  public ReadCurrency get(long id) {

    Currency currency = repository.findById(id).orElseThrow(EntityNotFoundException::new);
    return mapper.readCurrencyFromCurrency(currency);
  }


  @Transactional
  @Override
  public Page<ReadCurrency> getAll(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);
    Page<Currency> currencies = repository.findAll(pageable);
    return currencies.map(mapper::readCurrencyFromCurrency);
  }

  @Transactional
  @Override
  public List<String> getListCode() {

    List<Currency> currencies = repository.findAll();
    return currencies.stream().map(Currency::getCode).collect(
        Collectors.toList());
  }

  @Transactional
  @Override
  public ReadCurrency getCurrencyByCode(String code) {

    return mapper.readCurrencyFromCurrency(repository.getCurrenciesByCode(code));
  }

  private boolean currencyByIdIsPresent(long id) {
    if (repository.findById(id).isPresent()) {
      return true;
    } else {
      throw new EntityNotFoundException(
          String.format("Currency with id = '%s' could be found", id));
    }
  }
}
