package com.logistic.driverlogistic.service;

import com.logistic.driverlogistic.model.CreateDriver;
import com.logistic.driverlogistic.model.ReadDriver;

public interface DriverService extends DomainService<CreateDriver, ReadDriver> {

  void todayDriverBirthday();
}
