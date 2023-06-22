package com.logistic.driverlogistic.api.controller.driver;

import com.logistic.driverlogistic.api.model.CreateDriver;
import com.logistic.driverlogistic.api.model.ReadDriver;
import com.logistic.driverlogistic.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class DriverController implements DriverApi {

  private final DriverService service;

  @Override
  public CreateDriver addDriver(CreateDriver createDriver) {
    return service.createDriver(createDriver);
  }

  @Override
  public String deleteDriver(long id) {
    return service.deleteDriver(id);
  }

  @Override
  public ReadDriver updateDriver(ReadDriver readDriver, long id) {
    return service.updateDriver(readDriver,id);
  }

  @Override
  public ReadDriver getDriverById( long id) {
    return service.getDriverById(id);
  }
}
