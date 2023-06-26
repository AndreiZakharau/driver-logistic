package com.logistic.driverlogistic.mapper;

import static com.logistic.driverlogistic.mapper.AccountMapper.ACCOUNT_MAPPER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.logistic.driverlogistic.domain.Account;
import com.logistic.driverlogistic.model.CreateAccount;
import com.logistic.driverlogistic.model.ReadAccount;
import com.logistic.driverlogistic.testobject.ModelObject;
import org.junit.jupiter.api.Test;

class AccountMapperTest {

  @Test
  void accountFromCreateAccount() {

    CreateAccount createAccount = ModelObject.getCreateAccount();

    Account account = ACCOUNT_MAPPER.accountFromCreateAccount(createAccount);

    assertNotNull(account);
    assertEquals(account.getCash(), createAccount.getCash());
    assertEquals(account.getCurrency(), createAccount.getCurrency());
    assertEquals(account.getDriverId(), createAccount.getDriverId());
  }

  @Test
  void accountFromReadAccount() {

    ReadAccount readAccount = ModelObject.getReadAccount();

    Account account = ACCOUNT_MAPPER.accountFromReadAccount(readAccount);

    assertNotNull(account);
    assertEquals(account.getId(), readAccount.getId());
    assertEquals(account.getCash(), readAccount.getCash());
    assertEquals(account.getCurrency(), readAccount.getCurrency());
    assertEquals(account.getDriverId(), readAccount.getDriverId());
  }

  @Test
  void readAccountFromAccount() {

    Account account = ModelObject.getAccount();

    ReadAccount readAccount = ACCOUNT_MAPPER.readAccountFromAccount(account);

    assertNotNull(readAccount);
    assertEquals(readAccount.getId(), account.getId());
    assertEquals(readAccount.getCash(), account.getCash());
    assertEquals(readAccount.getCurrency(), account.getCurrency());
    assertEquals(readAccount.getDriverId(), account.getDriverId());
  }
}