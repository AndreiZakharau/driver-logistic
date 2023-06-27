package com.logistic.driverlogistic.api.controller.account;

import com.logistic.driverlogistic.model.ChangeBalanceModel;
import com.logistic.driverlogistic.model.CreateAccount;
import com.logistic.driverlogistic.model.ExchangeRates;
import com.logistic.driverlogistic.model.ReadAccount;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/account")
@Validated
public interface AccountApi {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ReadAccount addAccount(@Valid @RequestBody CreateAccount createAccount);

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteAccount(@PathVariable long id);

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadAccount updateAccount(@Valid @RequestBody CreateAccount createAccount, @PathVariable long id);

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadAccount getAccountById(@PathVariable long id);

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  Page<ReadAccount> findAllAccount(
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "size", defaultValue = "10", required = false) Integer size);

  @GetMapping("/{id}/cash/convert")
  @ResponseStatus(HttpStatus.OK)
  ReadAccount showConvertCash(@PathVariable long id,
      @Valid @RequestBody ExchangeRates exchangeRates);

  @PutMapping("/{id}/cash")
  @ResponseStatus(HttpStatus.OK)
  ReadAccount changeCashBalance(@PathVariable long id,
      @Valid @RequestBody ChangeBalanceModel changeBalanceModel);
}
