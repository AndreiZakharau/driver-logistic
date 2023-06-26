package com.logistic.driverlogistic.mapper;

import static com.logistic.driverlogistic.mapper.DriverMapper.DRIVER_MAPPER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.logistic.driverlogistic.model.CreateDriver;
import com.logistic.driverlogistic.model.ReadDriver;
import com.logistic.driverlogistic.domain.Driver;
import com.logistic.driverlogistic.testobject.ModelObject;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DriverMapperTest {

  @Test
  void driverFromCreateDriver() {

    CreateDriver createDriver = ModelObject.getCreateDriver();
    Driver driver = DRIVER_MAPPER.driverFromCreateDriver(createDriver);

    assertNotNull(driver);
    assertEquals(driver.getFullName(), createDriver.getFullName());
    assertEquals(driver.getPassportNumber(),createDriver.getPassportNumber());
    assertEquals(driver.getCategories(),createDriver.getCategories());
    assertEquals(driver.getBirthDay(), LocalDate.parse(createDriver.getBirthDay()));
  }

  @Test
  void driverFromReadDriver() {

    ReadDriver readDriver = ModelObject.getReadDriver();
    Driver driver = DRIVER_MAPPER.driverFromReadDriver(readDriver);

    assertNotNull(driver);
    assertEquals(driver.getId(), readDriver.getId());
    assertEquals(driver.getExperience(),readDriver.getExperience());
    assertEquals(driver.getBirthDay(), LocalDate.parse(readDriver.getBirthDay()));
    assertNotEquals(driver.getFullName(), "Sergei Ivanov");
  }

  @Test
  void createDriveFromDriver() {

    Driver driver = ModelObject.getDriver();
    CreateDriver createDriver = DRIVER_MAPPER.createDriveFromDriver(driver);

    assertNotNull(driver);
    assertEquals(driver.getFullName(), createDriver.getFullName());
    assertEquals(driver.getPassportNumber(),createDriver.getPassportNumber());
    assertEquals(driver.getCategories(),createDriver.getCategories());
    assertEquals(driver.getBirthDay(), LocalDate.parse(createDriver.getBirthDay()));
  }

  @Test
  void readDriverFromDriver() {

    Driver driver = ModelObject.getDriver();
    ReadDriver readDriver = DRIVER_MAPPER.readDriverFromDriver(driver);

    assertNotNull(driver);
    assertEquals(driver.getId(), readDriver.getId());
    assertEquals(driver.getExperience(),readDriver.getExperience());
    assertEquals(driver.getBirthDay(), LocalDate.parse(readDriver.getBirthDay()));

  }
}