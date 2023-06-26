package com.logistic.driverlogistic.api.controller.driver;

import com.logistic.driverlogistic.model.CreateDriver;
import com.logistic.driverlogistic.model.ReadDriver;
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

@RequestMapping("/driver")
@Validated
public interface DriverApi {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ReadDriver addDriver(@Valid @RequestBody CreateDriver createDriver);

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteDriver(@PathVariable long id);

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadDriver updateDriver(@RequestBody CreateDriver createDriver, @PathVariable long id);

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadDriver getDriverById(@PathVariable long id);

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  Page<ReadDriver> findAllCar(
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "size", defaultValue = "10", required = false) Integer size);


}
