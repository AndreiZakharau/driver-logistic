package com.logistic.driverlogistic.mapper;

import com.logistic.driverlogistic.domain.Account;
import com.logistic.driverlogistic.model.CreateAccount;
import com.logistic.driverlogistic.model.ReadAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

  AccountMapper ACCOUNT_MAPPER = Mappers.getMapper(AccountMapper.class);

  Account accountFromCreateAccount(CreateAccount createAccount);

  Account accountFromReadAccount(ReadAccount readAccount);

  ReadAccount readAccountFromAccount(Account account);

}
