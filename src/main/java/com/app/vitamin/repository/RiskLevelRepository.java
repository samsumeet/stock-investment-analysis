package com.app.vitamin.repository;

import com.app.vitamin.model.RiskLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskLevelRepository extends JpaRepository<RiskLevelEntity, Long> {

  RiskLevelEntity findByRiskLevelId(Integer riskLevel);
}
