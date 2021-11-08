package com.app.vitamin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "portfolio")
public class PortfolioEntity implements Serializable {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private int id;
  private String ticker;
  private String weight;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "risk_level_id",referencedColumnName = "riskLevelId")
  @JsonIgnoreProperties("portfolios")
  private RiskLevelEntity riskLevel;
}
