package com.app.vitamin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude = "riskId")
@Data
@Entity
@Table(name = "portfolio")
public class PortfolioEntity {

  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Id
  @Column(name = "id")
  private Long id;
  @Column(nullable = false)
  private String ticker;
  @Column(nullable = false)
  private String weight;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private RiskLevelEntity riskId;
}
