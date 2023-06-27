package com.logistic.driverlogistic.api.controller.account;

import com.logistic.driverlogistic.model.ChangeBalanceModel;
import com.logistic.driverlogistic.model.CreateAccount;
import com.logistic.driverlogistic.model.ExchangeRates;
import com.logistic.driverlogistic.model.ReadAccount;
import com.logistic.driverlogistic.service.impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountApi {

  private final AccountServiceImpl service;

  @Override
  public ReadAccount addAccount(CreateAccount createAccount) {

    return service.add(createAccount);
  }

  @Override
  public void deleteAccount(long id) {

    service.delete(id);
  }

  @Override
  public ReadAccount updateAccount(CreateAccount createAccount, long id) {

    return service.update(createAccount, id);
  }

  @Override
  public ReadAccount getAccountById(long id) {

    return service.get(id);
  }

  @Override
  public Page<ReadAccount> findAllAccount(Integer page, Integer size) {

    return service.getAll(page, size);
  }

  @Override
  public ReadAccount showConvertCash(long id, ExchangeRates exchangeRates) {

    return service.convertCash(id, exchangeRates);
  }

  @Override
  public ReadAccount changeCashBalance(
      long id, ChangeBalanceModel changeBalanceModel) {

    return service.changeCashBalance(id, changeBalanceModel);
  }
}
