package com.logistic.driverlogistic.api.controller.currency;

import com.logistic.driverlogistic.api.model.CurrencyView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/currency")
@Validated
public interface CurrencyApi {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  CurrencyView addCurrency(@Valid @RequestBody CurrencyView currencyView);

}
