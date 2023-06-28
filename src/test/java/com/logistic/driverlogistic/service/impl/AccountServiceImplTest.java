package com.logistic.driverlogistic.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.logistic.driverlogistic.domain.Account;
import com.logistic.driverlogistic.exception.BadParameterException;
import com.logistic.driverlogistic.mapper.AccountMapper;
import com.logistic.driverlogistic.mapper.CurrencyMapper;
import com.logistic.driverlogistic.model.CreateAccount;
import com.logistic.driverlogistic.model.ExchangeRates;
import com.logistic.driverlogistic.model.ReadAccount;
import com.logistic.driverlogistic.model.ReadCurrency;
import com.logistic.driverlogistic.repository.AccountRepository;
import com.logistic.driverlogistic.service.util.Converter;
import com.logistic.driverlogistic.testobject.ModelObject;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

  @InjectMocks
  AccountServiceImpl service;

  @Mock
  AccountRepository repository;

  @Mock
  AccountMapper mapper;

  @Mock
  CurrencyMapper currencyMapper;

  @Mock
  CurrencyServiceImpl currencyService;

  @Mock
  Converter converter;

  private final Account account = ModelObject.getAccount();
  private final ReadAccount readAccount = ModelObject.getReadAccount();
  private final CreateAccount createAccount = ModelObject.getCreateAccount();

  @Test
  void addAccount() {

    when(mapper.accountFromCreateAccount(createAccount)).thenReturn(account);
    when(repository.save(account)).thenReturn(account);
    when(mapper.readAccountFromAccount(account)).thenReturn(readAccount);

    ReadAccount actual = service.add(createAccount);

    assertNotNull(actual);
    assertEquals(actual.getCurrency().getId(), createAccount.getCurrency().getId());
    assertEquals(actual.getCash(), createAccount.getCash());
  }

  @Test
  void deleteAccount() {
    long id = 1L;

    when(repository.findById(id)).thenReturn(Optional.of(account));

    service.delete(id);

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).deleteById(id);
  }

  @Test
  void updateAccountIfChangeCurrencyThrowBadParameterException() {

    long id = 1L;
    String message = "You cannot change the 'currency' field here, first convert your account";
    ReadCurrency currencyCode = ReadCurrency.builder().id(2L).code("USD").country("USA").build();
    createAccount.setCurrency(currencyCode);

    when(repository.findById(id)).thenReturn(Optional.of(account));

    assertNotEquals(account.getCurrency().getCode(), currencyCode.getCode());

    Throwable exception = assertThrows(BadParameterException.class,
        () -> service.update(createAccount, id));

    assertNotNull(exception);
    assertEquals(exception.getMessage(), message);

  }

  @Test
  void getAccountById() {

    long id = 1L;

    when(repository.findById(id)).thenReturn(Optional.of(account));
    when(mapper.readAccountFromAccount(account)).thenReturn(readAccount);

    ReadAccount actual = service.get(id);

    assertNotNull(actual);
    assertEquals(actual.getCurrency().getId(), readAccount.getCurrency().getId());
    assertEquals(actual.getCash(), readAccount.getCash());
    assertEquals(actual.getDriverId(), readAccount.getDriverId());
  }

  @Test
  void convertCash() {

    Long id = 1L;
    List<String> listCode = List.of("RUS", "EUR", "USD");
    ExchangeRates rates = ExchangeRates.builder()
        .firstCode(ReadCurrency.builder().id(3L).code("USD").country("Russia").build())
        .secondCode(ReadCurrency.builder().id(1L).code("RUS").country("USA").build())
        .rate(78.34).build();
    ReadCurrency readCurrency = ReadCurrency.builder().id(3L).code("USD").country("USA").build();

    when(repository.findById(id)).thenReturn(Optional.of(account));
    when(mapper.readAccountFromAccount(account)).thenReturn(readAccount);
    when(currencyService.getListCode()).thenReturn(listCode);
    when(converter.returnCashAfterConvert(rates.getRate(), readAccount.getCash())).thenReturn(
        readAccount.getCash().multiply(
            BigDecimal.valueOf(rates.getRate())));

    ReadAccount readAccountAfterChange = readAccount;
    readAccountAfterChange.setCash(readAccount.getCash().multiply(
        BigDecimal.valueOf(rates.getRate())));
    readAccountAfterChange.setCurrency(readCurrency);

    ReadAccount actual = service.convertCash(id, rates);

    assertNotNull(actual);
    assertNotEquals(actual.getCash(), readAccount.getCash());
    assertEquals(actual.getCash(), readAccountAfterChange.getCash());
  }

}