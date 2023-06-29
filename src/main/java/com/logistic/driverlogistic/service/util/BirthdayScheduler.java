package com.logistic.driverlogistic.service.util;

import com.logistic.driverlogistic.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BirthdayScheduler {

  private final DriverService service;

  @Scheduled(cron = "0 0 7 * * *")
  public void startMessage() {

    service.todayDriverBirthday();
  }

}
