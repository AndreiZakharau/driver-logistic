package com.logistic.driverlogistic.service.impl;

import com.logistic.driverlogistic.domain.Account;
import com.logistic.driverlogistic.domain.Currency;
import com.logistic.driverlogistic.exception.BadParameterException;
import com.logistic.driverlogistic.mapper.AccountMapper;
import com.logistic.driverlogistic.mapper.CurrencyMapper;
import com.logistic.driverlogistic.model.ChangeBalanceModel;
import com.logistic.driverlogistic.model.CreateAccount;
import com.logistic.driverlogistic.model.ExchangeRates;
import com.logistic.driverlogistic.model.ReadAccount;
import com.logistic.driverlogistic.repository.AccountRepository;
import com.logistic.driverlogistic.service.AccountService;
import com.logistic.driverlogistic.service.util.Converter;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository repository;

  private final CurrencyServiceImpl currencyService;

  private final AccountMapper mapper;

  private final CurrencyMapper currencyMapper;

  private final Converter converter;

  @Transactional
  @Override
  public ReadAccount add(CreateAccount createAccount) {

    Account account =  repository.save(mapper.accountFromCreateAccount(createAccount));
    return mapper.readAccountFromAccount(account);
  }

  @Transactional
  @Override
  public void delete(long id) {

    accountByIdIsPresent(id);
    repository.deleteById(id);
  }

  @Override
  public ReadAccount update(CreateAccount createAccount, long id) {

    ReadAccount account = get(id);
    if (account.getCurrency().getId()!=(createAccount.getCurrency().getId())) {
      throw new BadParameterException(
          "You cannot change the 'currency' field here, first convert your account");
    }
    Account updateAccount = mapper.accountFromCreateAccount(createAccount);
    updateAccount.setId(id);
    return mapper.readAccountFromAccount(
        repository.save(updateAccount));
  }

  @Transactional
  @Override
  public ReadAccount get(long id) {

    Account account = repository.findById(id).orElseThrow(EntityNotFoundException::new);
    return mapper.readAccountFromAccount(account);
  }

  @Transactional
  @Override
  public Page<ReadAccount> getAll(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);
    Page<Account> accounts = repository.findAll(pageable);
    return accounts.map(mapper::readAccountFromAccount);
  }

  @Transactional(readOnly = true)
  @Override
  public ReadAccount convertCash(long id, ExchangeRates exchangeRates) {

    ReadAccount readAccount = get(id);
    List<String> codes = currencyService.getListCode();

    if (codes.contains(String.valueOf(exchangeRates.getFirstCode()))) {
      if (codes.contains(String.valueOf(exchangeRates.getSecondCode()))) {
        BigDecimal cash = converter.returnCashAfterConvert(exchangeRates.getRate(),
            readAccount.getCash());
        readAccount.setCash(cash);
        readAccount.setCurrency(exchangeRates.getSecondCode());
        return readAccount;
      } else {
        throw new EntityNotFoundException(String.format("Code = '%s' could be found",
            exchangeRates.getSecondCode()));
      }
    } else {
      throw new EntityNotFoundException(String.format("Code = '%s' could be found",
          exchangeRates.getFirstCode()));
    }
  }

  @Transactional
  @Override
  public ReadAccount changeCashBalance(long id, ChangeBalanceModel model) {

    ReadAccount readAccount = get(id);
    Currency currency = currencyMapper.currencyFromReadCurrency(
        currencyService.getCurrencyByCode(model.getCode()));
    currency.setCode(model.getCode());
    if (!String.valueOf(readAccount.getCurrency()).equals(model.getCode())) {
      ExchangeRates rate = model.getRates().stream().filter(exchangeRates ->
          exchangeRates.getFirstCode().equals(currency)
              && exchangeRates.getSecondCode().equals(readAccount.getCurrency())).toList().get(0);
      BigDecimal convertCash = converter.returnCashAfterConvert(rate.getRate(), model.getCash());
      readAccount.setCash(convertCash.add(readAccount.getCash()));
    }
    return readAccount;
  }

  private boolean accountByIdIsPresent(long id) {
    if (repository.findById(id).isPresent()) {
      return true;
    } else {
      throw new EntityNotFoundException(String.format("Account with id = '%s' could be found", id));
    }
  }
}
