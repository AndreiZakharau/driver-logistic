package com.logistic.driverlogistic.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Entity
public class Driver {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(min = 1, max = 50)
  private String categories;

  @NotNull
  @Size(min = 1, max = 150)
  private String fullName;

  @NotNull
  @Column(unique = true)
  @Size(min = 1, max = 50)
  private String passportNumber;

  @NotNull
  private Byte experience;

  @NotNull
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate birthDay;

  @NotNull
  @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
 @PrimaryKeyJoinColumn
  private Account account;
}
