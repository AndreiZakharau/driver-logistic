package com.logistic.driverlogistic.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.logistic.driverlogistic.domain.Currency;
import com.logistic.driverlogistic.mapper.CurrencyMapper;
import com.logistic.driverlogistic.model.CreateCurrency;
import com.logistic.driverlogistic.model.ReadCurrency;
import com.logistic.driverlogistic.repository.CurrencyRepository;
import com.logistic.driverlogistic.testobject.ModelObject;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

  @InjectMocks
  CurrencyServiceImpl service;

  @Mock
  CurrencyMapper mapper;

  @Mock
  CurrencyRepository repository;

  private final Currency currency = ModelObject.getCurrency();

  private final ReadCurrency readCurrency = ModelObject.getReadCurrency();

  private final CreateCurrency createCurrency = ModelObject.getCreateCurrency();

  @Test
  void addCurrency() {

    when(mapper.currencyFromCreateCurrency(createCurrency)).thenReturn(currency);
    when(repository.save(currency)).thenReturn(currency);
    when(mapper.readCurrencyFromCurrency(currency)).thenReturn(readCurrency);

    ReadCurrency actual = service.add(createCurrency);

    assertNotNull(actual);
    assertEquals(actual.getCode(), createCurrency.getCode());
    assertEquals(actual.getCountry(), createCurrency.getCountry());
  }

  @Test
  void deleteCurrency() {

    long id = 1L;

    when(repository.findById(id)).thenReturn(Optional.of(currency));

    service.delete(id);

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).deleteById(id);
  }


  @Test
  void updateCurrency() {

    long id = 1L;
    String country = "Rus";
    createCurrency.setCountry(country);

    when(repository.findById(id)).thenReturn(Optional.of(currency));

    assertNotEquals(currency.getCountry(), country);

    currency.setCountry(country);
    readCurrency.setCountry(country);

    when(mapper.currencyFromCreateCurrency(createCurrency)).thenReturn(currency);
    when(repository.save(currency)).thenReturn(currency);
    when(mapper.readCurrencyFromCurrency(currency)).thenReturn(readCurrency);

    ReadCurrency actual = service.update(createCurrency, id);

    assertNotNull(actual);
    assertEquals(actual.getId(), readCurrency.getId());
    assertEquals(actual.getCountry(), readCurrency.getCountry());
    assertEquals(actual.getCode(), readCurrency.getCode());
    ;

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).save(currency);
  }

  @Test
  void deleteCurrencyByIdIfNoSuchIdThenThrowEntityNotFoundException() {

    long id = 3L;
    String exceptionMessage = String.format("Currency with id = '%s' could be found", id);

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(EntityNotFoundException.class,
        () -> service.delete(id));
    assertNotNull(exception.getMessage());
    assertEquals(exception.getMessage(), exceptionMessage);

    verify(repository, times(1)).findById(id);
    verify(repository, times(0)).deleteById(id);
  }

  @Test
  void getCurrencyById() {

    long id = 1L;

    when(repository.findById(id)).thenReturn(Optional.of(currency));
    when(mapper.readCurrencyFromCurrency(currency)).thenReturn(readCurrency);

    ReadCurrency actual = service.get(id);

    assertNotNull(actual);
    assertEquals(actual.getId(), currency.getId());
    assertEquals(actual.getCountry(), currency.getCountry());
    assertEquals(actual.getCode(), currency.getCode());

    verify(repository, times(1)).findById(id);
  }

  @Test
  void getAll() {

    when(repository.findAll((Pageable) any())).thenReturn(Page.empty());
    service.getAll(0, 10);
    verify(repository).findAll((Pageable) any());
  }

  @Test
  void getListCode() {

    List<String> listCode = List.of("RUS", "EUR", "USD");
    List<Currency> currencies = ModelObject.getListCurrency();
    when(repository.findAll()).thenReturn(currencies);

    List<String> actual = service.getListCode();

    assertNotNull(actual);
    assertEquals(actual.size(), listCode.size());
  }
}