package com.app.vitamin.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class PortfolioResponse {

  private List<StockDetails> stockDetails;
}
