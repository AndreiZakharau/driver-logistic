package com.logistic.driverlogistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DriverLogisticApplication {

  public static void main(String[] args) {
    SpringApplication.run(DriverLogisticApplication.class, args);
  }

}
