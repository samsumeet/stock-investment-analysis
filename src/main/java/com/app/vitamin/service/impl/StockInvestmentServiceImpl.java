package com.app.vitamin.service.impl;

import com.app.vitamin.domain.PortfolioRequest;
import com.app.vitamin.domain.StockDetails;
import com.app.vitamin.exception.NotFoundException;
import com.app.vitamin.model.PortfolioEntity;
import com.app.vitamin.repository.RiskLevelRepository;
import com.app.vitamin.service.StockInvestmentService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StockInvestmentServiceImpl implements StockInvestmentService {

  @Autowired
  private RiskLevelRepository riskLevelRepository;
  @Autowired
  private CurrentStockPriceDetails currentStockPriceDetails;

  @Override
  public Set<PortfolioEntity> getPortfolioForRisk(Integer riskLevel) {
    log.info("Fetch Portfolio on basis of provided Risk {}", riskLevel);

    return riskLevelRepository.findByRiskLevelId(riskLevel).getPortfolios();
  }

  @Override
  public List<StockDetails> getCurrentPortfolio(PortfolioRequest portfolioRequest) {
    log.info("Fetch Portfolio on basis of provided Risk {}", portfolioRequest);

    long totalMonth = (ChronoUnit.MONTHS
        .between(portfolioRequest.getFrom(), portfolioRequest.getTo()) + 1);

    Set<PortfolioEntity> portfolioList = riskLevelRepository
        .findByRiskLevelId(portfolioRequest.getRiskLevel()).getPortfolios();

    if (portfolioList.isEmpty()) {
      throw new NotFoundException("No Portfolio Found for given risk level");
    }
    return portfolioList.stream().map(portfolioEntity -> {
      double totalInvestedAmount = totalMonth * Double.parseDouble(portfolioEntity.getWeight())
          * portfolioRequest.getMonthlyInvestment();
      double totalNoOfStocks = (totalInvestedAmount / currentStockPriceDetails
          .getCurrentStockPrice(portfolioEntity.getTicker()));

      StockDetails stockDetails = new StockDetails();
      stockDetails.setStockName(portfolioEntity.getTicker());

      stockDetails.setInvestedAmountMonthly(
          BigDecimal.valueOf(Float.parseFloat(portfolioEntity.getWeight()) * portfolioRequest
              .getMonthlyInvestment()).setScale(2, RoundingMode.HALF_UP));

      stockDetails.setTotalInvestment(BigDecimal.valueOf(totalInvestedAmount).setScale(2,
          RoundingMode.HALF_UP));
      stockDetails.setTotalNoOfStock(BigDecimal.valueOf(totalNoOfStocks).setScale(2,
          RoundingMode.HALF_UP));

      return stockDetails;

    }).collect(Collectors.toList());
  }
}
