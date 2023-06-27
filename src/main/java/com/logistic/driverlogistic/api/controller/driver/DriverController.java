package com.logistic.driverlogistic.api.controller.driver;

import com.logistic.driverlogistic.model.CreateDriver;
import com.logistic.driverlogistic.model.ReadDriver;
import com.logistic.driverlogistic.service.impl.DriverServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DriverController implements DriverApi {

  private final DriverServiceImpl service;

  @Override
  public ReadDriver addDriver(CreateDriver createDriver) {
    return service.add(createDriver);
  }

  @Override
  public void deleteDriver(long id) {
     service.delete(id);
  }

  @Override
  public ReadDriver updateDriver(CreateDriver createDriver, long id) {
    return service.update(createDriver,id);
  }

  @Override
  public ReadDriver getDriverById( long id) {
    return service.get(id);
  }

  @Override
  public Page<ReadDriver> findAllDriver(Integer page, Integer size) {
    return service.getAll(page,size);
  }
}
