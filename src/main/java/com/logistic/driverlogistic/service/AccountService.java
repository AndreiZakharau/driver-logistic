package com.logistic.driverlogistic.service;

import com.logistic.driverlogistic.model.ChangeBalanceModel;
import com.logistic.driverlogistic.model.CreateAccount;
import com.logistic.driverlogistic.model.ExchangeRates;
import com.logistic.driverlogistic.model.ReadAccount;

public interface AccountService extends DomainService<CreateAccount, ReadAccount> {


  ReadAccount convertCash(long id, ExchangeRates exchangeRates);

  ReadAccount changeCashBalance(long id, ChangeBalanceModel model);
}
