package com.app.vitamin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude = "portfolios")
@Data
@Entity
@Table(name = "risk_level")
public class RiskLevelEntity {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;
  @Column(nullable = false)
  private Integer riskLevelId;

  @OneToMany(mappedBy = "riskId", cascade = CascadeType.ALL,
      fetch = FetchType.EAGER)
  private Set<PortfolioEntity> portfolios=new HashSet<>();

  public void setPortfolios(PortfolioEntity portfolioEntity){
    this.portfolios.add(portfolioEntity);
    portfolioEntity.setRiskId(this);
  }
}
