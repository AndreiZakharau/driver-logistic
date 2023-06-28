package com.logistic.driverlogistic.repository;

import com.logistic.driverlogistic.domain.Currency;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {

  void deleteById(Long id);

  Optional<Currency> findById(Long id);

  Currency getCurrenciesByCode(String code);
}
