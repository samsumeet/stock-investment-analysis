package com.app.vitamin.domain;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class StockDetails {
  private String stockName;
  private BigDecimal investedAmountMonthly;
  private BigDecimal totalInvestment;
  private BigDecimal totalNoOfStock;


}
