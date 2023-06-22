package com.logistic.driverlogistic.mapper;

import com.logistic.driverlogistic.api.model.CreateDriver;
import com.logistic.driverlogistic.api.model.ReadDriver;
import com.logistic.driverlogistic.domain.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DriverMapper {

  DriverMapper DRIVER_MAPPER = Mappers.getMapper(DriverMapper.class);

  Driver driverFromCreateDriver(CreateDriver createDriver);
  Driver driverFromReadDriver(ReadDriver readDriver);
  CreateDriver createDriveFromDriver(Driver driver);
  ReadDriver readDriverFromDriver(Driver driver);



}
