package com.app.vitamin.controller.impl;

import com.app.vitamin.controller.StockInvestmentController;
import com.app.vitamin.domain.PortfolioRequest;
import com.app.vitamin.domain.RiskLevelRequest;
import com.app.vitamin.domain.StockDetails;
import com.app.vitamin.model.PortfolioEntity;
import com.app.vitamin.service.StockInvestmentService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class StockInvestmentControllerImpl implements StockInvestmentController {

  @Autowired
  private StockInvestmentService stockInvestmentService;

  @Override
  public ResponseEntity<Set<PortfolioEntity>> getPortfolioForRisk(RiskLevelRequest riskLevel) {
    return ResponseEntity
        .ok(stockInvestmentService.getPortfolioForRisk(riskLevel.getRequestLevel()));
  }

  @Override
  public ResponseEntity<List<StockDetails>> getCurrentPortfolio(PortfolioRequest portfolioRequest) {
    return ResponseEntity.ok(stockInvestmentService.getCurrentPortfolio(portfolioRequest));
  }
}
