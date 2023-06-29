package com.logistic.driverlogistic.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.logistic.driverlogistic.domain.Driver;
import com.logistic.driverlogistic.mapper.DriverMapper;
import com.logistic.driverlogistic.model.CreateDriver;
import com.logistic.driverlogistic.model.ReadDriver;
import com.logistic.driverlogistic.repository.DriverRepository;
import com.logistic.driverlogistic.testobject.ModelObject;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {

  @InjectMocks
  DriverServiceImpl service;

  @Mock
  DriverRepository repository;

  @Mock
  DriverMapper mapper;

  @Mock
  Logger logger;


  private final Driver driver = ModelObject.getDriver();
  private final ReadDriver readDriver = ModelObject.getReadDriver();
  private final CreateDriver createDriver = ModelObject.getCreateDriver();


  @Test
  void saveDriver() {

    when(mapper.driverFromCreateDriver(createDriver)).thenReturn(driver);
    when(repository.save(driver)).thenReturn(driver);
    when(mapper.readDriverFromDriver(driver)).thenReturn(readDriver);

    ReadDriver actual = service.add(createDriver);

    assertNotNull(actual);
    assertEquals(actual.getBirthDay(), readDriver.getBirthDay());
    assertEquals(actual.getExperience(), readDriver.getExperience());
    assertEquals(actual.getCategories(), readDriver.getCategories());
    assertEquals(actual.getFullName(), readDriver.getFullName());
    assertEquals(actual.getPassportNumber(), readDriver.getPassportNumber());

  }

  @Test
  void deleteDriverById() {

    long id = 1L;

    when(repository.findById(id)).thenReturn(Optional.of(driver));

    service.delete(id);

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).deleteById(id);
  }

  @Test
  void deleteDriverByIdIfNoSuchIdThenThrowEntityNotFoundException() {

    long id = 6L;
    String exceptionMessage = String.format("Driver with id = '%s' could be found", id);

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(EntityNotFoundException.class,
        () -> service.delete(id));
    assertNotNull(exception.getMessage());
    assertEquals(exception.getMessage(), exceptionMessage);

    verify(repository, times(1)).findById(id);
    verify(repository, times(0)).deleteById(id);

  }

  @Test
  void updateDriverById() {

    long id = 1L;
    String fullName = "Ivan Petrov";
    createDriver.setFullName(fullName);

    when(repository.findById(id)).thenReturn(Optional.of(driver));

    assertNotEquals(driver.getFullName(), fullName);

    driver.setFullName(fullName);
    readDriver.setFullName(fullName);

    when(mapper.driverFromCreateDriver(createDriver)).thenReturn(driver);
    when(repository.save(driver)).thenReturn(driver);
    when(mapper.readDriverFromDriver(driver)).thenReturn(readDriver);

    ReadDriver actual = service.update(createDriver, id);

    assertNotNull(actual);
    assertEquals(actual.getId(), readDriver.getId());
    assertEquals(actual.getBirthDay(), readDriver.getBirthDay());
    assertEquals(actual.getExperience(), readDriver.getExperience());
    assertEquals(actual.getCategories(), readDriver.getCategories());
    assertEquals(actual.getFullName(), readDriver.getFullName());
    assertEquals(actual.getPassportNumber(), readDriver.getPassportNumber());

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).save(driver);
  }

  @Test
  void getDriverById() {

    long id = 1L;

    when(repository.findById(id)).thenReturn(Optional.of(driver));
    when(mapper.readDriverFromDriver(driver)).thenReturn(readDriver);

    ReadDriver actual = service.get(id);

    assertNotNull(actual);
    assertEquals(actual.getId(), readDriver.getId());
    assertEquals(actual.getBirthDay(), readDriver.getBirthDay());
    assertEquals(actual.getExperience(), readDriver.getExperience());
    assertEquals(actual.getCategories(), readDriver.getCategories());
    assertEquals(actual.getFullName(), readDriver.getFullName());
    assertEquals(actual.getPassportNumber(), readDriver.getPassportNumber());

    verify(repository, times(1)).findById(id);
  }

  @Test
  void getDriverByIdIfNoSuchIdThenThrowEntityNotFoundException() {

    long id = 5L;

    when(repository.findById(id)).thenReturn(Optional.empty());

    Throwable exception = assertThrows(EntityNotFoundException.class, () -> service.get(id));
    assertNull(exception.getMessage());

    verify(repository, times(1)).findById(id);
  }

  @Test
  void  testDriverBirthday() {

    Driver driver1 = Driver.builder()
        .id(3L)
        .fullName("Ban Smith")
        .categories("C,CE")
        .experience((byte) 7)
        .birthDay(LocalDate.parse("1995-12-23"))
        .passportNumber("MC5677887").build();

    Driver driver2 = Driver.builder()
        .id(45L)
        .fullName("Ron Dick")
        .categories("C,CE,D,DE")
        .experience((byte) 7)
        .birthDay(LocalDate.now().minusYears(25))
        .passportNumber("MA4534321").build();

    List<Driver> drivers = List.of(driver1, driver2);

    when(repository.findAll()).thenReturn(drivers);

    service.todayDriverBirthday();

    verify(repository,times(1)).findAll();
  }
}