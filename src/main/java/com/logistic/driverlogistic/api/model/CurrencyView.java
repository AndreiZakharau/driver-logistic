package com.logistic.driverlogistic.api.model;

import jakarta.validation.constraints.NotNull;

public record CurrencyView(@NotNull(message = "Value code cannot be null")
                           String code,
                           @NotNull(message = "Value country cannot be null")
                           String country) {

}
