package com.logistic.driverlogistic.api.controller.driver;

import com.logistic.driverlogistic.model.CreateDriver;
import com.logistic.driverlogistic.model.ReadDriver;
import com.logistic.driverlogistic.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DriverController implements DriverApi {

  private final DriverService service;

  @Override
  public ReadDriver addDriver(CreateDriver createDriver) {
    return service.createDriver(createDriver);
  }

  @Override
  public void deleteDriver(long id) {
     service.deleteDriver(id);
  }

  @Override
  public ReadDriver updateDriver(CreateDriver createDriver, long id) {
    return service.updateDriver(createDriver,id);
  }

  @Override
  public ReadDriver getDriverById( long id) {
    return service.getDriverById(id);
  }

  @Override
  public Page<ReadDriver> findAllCar(Integer page, Integer size) {
    return service.getAllDriver(page,size);
  }
}
