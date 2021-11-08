package com.app.vitamin.controller;

import com.app.vitamin.domain.RiskLevelRequest;
import com.app.vitamin.model.PortfolioEntity;
import java.util.List;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/me/investment-portfolio")
public interface StockInvestmentController {

  @PostMapping(value = "/")
  ResponseEntity<Set<PortfolioEntity>> getPortfolioForRisk(
      @RequestBody RiskLevelRequest riskLevel);

}
