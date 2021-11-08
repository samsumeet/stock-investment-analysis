package com.app.vitamin.service.impl;

import com.app.vitamin.model.PortfolioEntity;
import com.app.vitamin.repository.RiskLevelRepository;
import com.app.vitamin.service.StockInvestmentService;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StockInvestmentServiceImpl implements StockInvestmentService {

  @Autowired
  private RiskLevelRepository riskLevelRepository;

  @Override
  public Set<PortfolioEntity> getPortfolioForRisk(Integer riskLevel) {
    log.info("Fetch Portfolio on basis of provided Risk {}", riskLevel);

    return  riskLevelRepository.findAll().get(0).getPortfolios();
  }
}
