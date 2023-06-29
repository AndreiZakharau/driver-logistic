package com.logistic.driverlogistic.service.impl;

import com.logistic.driverlogistic.domain.Driver;
import com.logistic.driverlogistic.mapper.DriverMapper;
import com.logistic.driverlogistic.model.CreateDriver;
import com.logistic.driverlogistic.model.ReadDriver;
import com.logistic.driverlogistic.repository.DriverRepository;
import com.logistic.driverlogistic.service.DriverService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

  private final DriverRepository driverRepository;

  private final DriverMapper driverMapper;

  private final Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);


  @Transactional
  @Override
  public ReadDriver add(CreateDriver createDriver) {

    return driverMapper.readDriverFromDriver(
        driverRepository.save(driverMapper.driverFromCreateDriver(createDriver)));
  }

  @Transactional
  @Override
  public void delete(long id) {

    driverByIdIsPresent(id);
    driverRepository.deleteById(id);
  }

  @Transactional
  @Override
  public ReadDriver update(CreateDriver createDriver, long id) {

    driverByIdIsPresent(id);
    Driver driver = driverMapper.driverFromCreateDriver(createDriver);
    driver.setId(id);
    return driverMapper.readDriverFromDriver(
        driverRepository.save(driver));
  }

  @Transactional
  @Override
  public ReadDriver get(long id) {

    Driver driver = driverRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    return driverMapper.readDriverFromDriver(driver);
  }

  @Transactional
  @Override
  public Page<ReadDriver> getAll(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);
    Page<Driver> cars = driverRepository.findAll(pageable);
    return cars.map(driverMapper::readDriverFromDriver);
  }

  @Transactional(readOnly = true)
  @Override
  public void todayDriverBirthday() {

    List<Driver> drivers = driverRepository.findAll();
    for (Driver driver : drivers) {
      if (driver.getBirthDay().getDayOfMonth() == LocalDate.now().getDayOfMonth() &&
          driver.getBirthDay().getMonthValue() == LocalDate.now().getMonthValue()) {
        int age = LocalDate.now().getYear() - driver.getBirthDay().getYear();
        String message = String.format(
            "Today is the driver's birthday - %s, him(her) today - %s, him(her) ID - %s",
            driver.getFullName(), age, driver.getId());
        logger.info(message);
      }
    }
  }

  private boolean driverByIdIsPresent(long id) {
    if (driverRepository.findById(id).isPresent()) {
      return true;
    } else {
      throw new EntityNotFoundException(String.format("Driver with id = '%s' could be found", id));
    }
  }
}
