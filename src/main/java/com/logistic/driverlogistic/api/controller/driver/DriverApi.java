package com.logistic.driverlogistic.api.controller.driver;

import com.logistic.driverlogistic.api.model.CreateDriver;
import com.logistic.driverlogistic.api.model.ReadDriver;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/driver")
@Validated
public interface DriverApi {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  CreateDriver addDriver(@Valid @RequestBody CreateDriver createDriver);

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  String deleteDriver(@PathVariable long id);

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadDriver updateDriver(@RequestBody ReadDriver readDriver,@PathVariable long id);

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  ReadDriver getDriverById (@PathVariable long id);


}
