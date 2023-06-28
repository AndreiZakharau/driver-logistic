package com.logistic.driverlogistic.repository;

import com.logistic.driverlogistic.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
