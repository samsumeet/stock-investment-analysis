package com.app.vitamin.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.mockito.Mockito.when;

import com.app.vitamin.exception.NotFoundException;
import com.app.vitamin.model.PortfolioEntity;
import com.app.vitamin.model.RiskLevelEntity;
import com.app.vitamin.repository.RiskLevelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class StockInvestmentServiceTests {

  @Autowired
  private StockInvestmentService stockInvestmentService;

  @MockBean
  private RiskLevelRepository riskLevelRepository;

  private RiskLevelEntity riskLevelEntity = new RiskLevelEntity();


  @BeforeEach
  void setup() {

    PortfolioEntity portfolioEntity = new PortfolioEntity();
    portfolioEntity.setId(1L);
    portfolioEntity.setWeight("0.25");
    portfolioEntity.setTicker("test");

    riskLevelEntity.setRiskLevelId(4);
    riskLevelEntity.setPortfolios(portfolioEntity);
  }

  @Test
  void testPortfolioExistsForRiskSuccess() {

    when(riskLevelRepository.findByRiskLevelId(4)).thenReturn(riskLevelEntity);

    assertThat(stockInvestmentService.getPortfolioForRisk(4), contains(
        hasProperty("ticker", is("test"))));

  }

  @Test
  void testExpectedExceptionFail() {

    when(riskLevelRepository.findByRiskLevelId(4)).thenReturn(riskLevelEntity);

    Exception thrown = Assertions
        .assertThrows(NotFoundException.class, () -> {
          stockInvestmentService.getPortfolioForRisk(3);
        }, "NotFoundException error was expected");

    Assertions.assertEquals("No Portfolio Found for given risk level", thrown.getMessage());
  }
}
