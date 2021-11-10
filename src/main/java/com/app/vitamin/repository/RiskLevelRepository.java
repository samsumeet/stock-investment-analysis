package com.app.vitamin.repository;

import com.app.vitamin.model.PortfolioEntity;
import com.app.vitamin.model.RiskLevelEntity;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskLevelRepository extends JpaRepository<RiskLevelEntity, Long> {

  RiskLevelEntity findByRiskLevelId(Integer riskLevel);
}
