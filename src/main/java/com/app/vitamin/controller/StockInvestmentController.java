package com.app.vitamin.controller;

import com.app.vitamin.domain.PortfolioRequest;
import com.app.vitamin.domain.RiskLevelRequest;
import com.app.vitamin.domain.StockDetails;
import com.app.vitamin.model.PortfolioEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

  @ApiOperation(value = "Fetch Portfolio matching Risk Level")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 500, message = "Failure")})
  @PostMapping
  ResponseEntity<Set<PortfolioEntity>> getPortfolioForRisk(
      @RequestBody RiskLevelRequest riskLevel);


  @ApiOperation(value = "Fetch Portfolio matching Risk Level")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 500, message = "Failure")})
  @PostMapping(value = "/current-value")
  ResponseEntity<List<StockDetails>> getCurrentPortfolio(
      @RequestBody PortfolioRequest portfolioRequest);

}
