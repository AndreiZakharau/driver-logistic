package com.logistic.driverlogistic.api.controller.currency;

import com.logistic.driverlogistic.model.CreateCurrency;
import com.logistic.driverlogistic.model.ReadCurrency;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Currency API", description = "Currency api interface")
public interface CurrencyApi {

  @Operation(summary = "Create currency", description = "Create new currency")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ReadCurrency addCurrency(@Valid @RequestBody CreateCurrency createCurrency);

  @Operation(summary = "Delete currency", description = "Delete currency by ID")
  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteCurrency(@PathVariable Long id);

  @Operation(summary = "Update currency", description = "Update currency by ID")
  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadCurrency updateCurrency(@RequestBody CreateCurrency createCurrency, @PathVariable Long id);

  @Operation(summary = "Read currency", description = "Get currency by ID")
  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadCurrency getCurrencyById(@PathVariable Long id);

  @Operation(summary = "Read all currencies", description = "Get all currencies with pagination")
  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  Page<ReadCurrency> findAllCurrency(
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "size", defaultValue = "10", required = false) Integer size);

}
