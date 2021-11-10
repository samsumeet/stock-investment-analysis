package com.app.vitamin.service;

import com.app.vitamin.domain.PortfolioRequest;
import com.app.vitamin.domain.PortfolioResponse;
import com.app.vitamin.domain.StockDetails;
import com.app.vitamin.model.PortfolioEntity;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public interface StockInvestmentService {

  Set<PortfolioEntity> getPortfolioForRisk(Integer riskLevel);

  List<StockDetails> getCurrentPortfolio(PortfolioRequest portfolioRequest);

}
