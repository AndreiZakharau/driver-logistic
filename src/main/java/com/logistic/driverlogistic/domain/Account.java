package com.logistic.driverlogistic.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Entity
public class Account {

  @Id
  @Column(name = "driver_id")
  private Long id;

  private BigDecimal cash;

  @OneToOne
  @JoinColumn(name = "currency_id")
  private Currency currency;

  @OneToOne
  @MapsId
  @JoinColumn(name = "driver_id")
  private Driver driver;

}
