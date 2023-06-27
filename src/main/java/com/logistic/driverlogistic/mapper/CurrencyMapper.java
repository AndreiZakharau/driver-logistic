package com.logistic.driverlogistic.mapper;

import com.logistic.driverlogistic.domain.Currency;
import com.logistic.driverlogistic.model.CreateCurrency;
import com.logistic.driverlogistic.model.ReadCurrency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

  CurrencyMapper CURRENCY_MAPPER = Mappers.getMapper(CurrencyMapper.class);

  Currency currencyFromReadCurrency(ReadCurrency readCurrency);

  ReadCurrency readCurrencyFromCurrency(Currency currency);

  Currency currencyFromCreateCurrency(CreateCurrency createCurrency);
}
