package com.logistic.driverlogistic.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Model create driver")
public class CreateDriver {


  @NotNull(message = "Value 'categories' cannot be null")
  @Size(min = 1, max = 50, message = "Value 'categories' size must be between 1 and 50")
  private String categories;

  @NotNull(message = "Value 'fullName' cannot be null")
  @Size(min = 1, max = 150, message = "Value 'fullName' size must be between 1 and 150")
  @Pattern(regexp = "^[\\p{L}\\s]*$", message = "Value 'fullName' must use only letters and spaces")
  private String fullName;

  @NotNull(message = "Value 'passportNumber' cannot be null")
  @Size(min = 1, max = 50, message = "Value 'passportNumber' size must be between 1 and 50")
  @Pattern(regexp = "[A-Z0-9]*", message = "Value 'passportNumber' must use only latin letters and numbers")
  private String passportNumber;

  @NotNull(message = "Value 'experience; cannot be null")
  private Byte experience;

  @NotNull(message = "Value 'birthDay' cannot be null")
  private String birthDay;

  private CreateAccount account;

}
