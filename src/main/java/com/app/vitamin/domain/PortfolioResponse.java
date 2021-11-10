package com.app.vitamin.domain;

import java.util.List;
import lombok.Data;

@Data
public class PortfolioResponse {

  private List<StockDetails> stockDetails;
}
