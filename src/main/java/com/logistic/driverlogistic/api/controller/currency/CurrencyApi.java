package com.logistic.driverlogistic.api.controller.currency;

import com.logistic.driverlogistic.model.CreateCurrency;
import com.logistic.driverlogistic.model.ReadCurrency;
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

@RequestMapping("/currency")
@Validated
public interface CurrencyApi {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ReadCurrency addCurrency(@Valid @RequestBody CreateCurrency createCurrency);

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteCurrency(@PathVariable Long id);

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadCurrency updateCurrency(@RequestBody CreateCurrency createCurrency, @PathVariable Long id);

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadCurrency getCurrencyById(@PathVariable Long id);

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  Page<ReadCurrency> findAllCurrency(
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "size", defaultValue = "10", required = false) Integer size);

}
