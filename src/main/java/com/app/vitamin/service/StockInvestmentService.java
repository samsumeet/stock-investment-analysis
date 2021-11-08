package com.app.vitamin.service;

import com.app.vitamin.model.PortfolioEntity;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public interface StockInvestmentService {

  Set<PortfolioEntity> getPortfolioForRisk(Integer riskLevel);
}
