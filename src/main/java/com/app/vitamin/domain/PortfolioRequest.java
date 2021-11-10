package com.app.vitamin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class PortfolioRequest {
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate from;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate to;
  private Integer riskLevel;
  private float monthlyInvestment;
}
