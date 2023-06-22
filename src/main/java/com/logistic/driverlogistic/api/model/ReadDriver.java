package com.logistic.driverlogistic.api.model;

import com.logistic.driverlogistic.domain.Account;
import java.time.LocalDate;

public record ReadDriver(long id,
                         String categories,
                         String fullName,
                         String passportNumber,
                         Account driverAccount,
                         Byte experience,
                         LocalDate birthDay) {

}
