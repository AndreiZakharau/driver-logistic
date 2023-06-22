package com.logistic.driverlogistic.service;

import com.logistic.driverlogistic.api.model.CreateDriver;
import com.logistic.driverlogistic.api.model.ReadDriver;
import com.logistic.driverlogistic.domain.Driver;
import com.logistic.driverlogistic.mapper.DriverMapper;
import com.logistic.driverlogistic.repository.DriverRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DriverService {

  private final DriverRepository driverRepository;

  private final DriverMapper driverMapper;

  @Transactional
  public CreateDriver createDriver(CreateDriver driver) {

    return driverMapper.createDriveFromDriver(
        driverRepository.save(driverMapper.driverFromCreateDriver(driver)));
  }

  @Transactional
  public String deleteDriver(long id) {
    String message = "Driver was delete.";
    if (driverByIdIsPresent(id)) {
      driverRepository.deleteById(id);
    } else {
      message = String.format("Driver with %s wasn't delete.", id);
    }
    return message;
  }

  //ToDo
  @Transactional
  public ReadDriver updateDriver(ReadDriver readDriver, long id) {

    if (driverByIdIsPresent(id)) {
      driverRepository.saveAndFlush(driverMapper.driverFromReadDriver(readDriver));
    }
    return readDriver;
  }

  @Transactional
  public ReadDriver getDriverById(long id) {

    Optional<Driver> driver = Optional.of(driverRepository.findById(id)).orElseThrow();
    return driverMapper.readDriverFromDriver(driver.get());
  }

  private boolean driverByIdIsPresent(long id) {
    if (driverRepository.findById(id).isPresent()) {
      return true;
    } else {
      return false;
    }
  }
}
