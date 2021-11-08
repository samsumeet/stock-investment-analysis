package com.app.vitamin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "risk_level")
public class RiskLevelEntity implements Serializable {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private int id;
  private int riskLevelId;

  @OneToMany(mappedBy = "riskLevel", fetch = FetchType.EAGER,
      cascade = CascadeType.ALL)
  @JsonIgnoreProperties("riskLevel")
  private Set<PortfolioEntity> portfolios;
}
