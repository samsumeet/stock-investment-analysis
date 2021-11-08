package com.app.vitamin.service.impl;

import com.app.vitamin.model.PortfolioEntity;
import com.app.vitamin.model.RiskLevelEntity;
import com.app.vitamin.repository.RiskLevelRepository;
import com.app.vitamin.service.PortfoliosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PortfolioServiceImpl implements PortfoliosService {

  private static final String TICKER = "ticker";
  private static final String WEIGHT = "weight";

  @Autowired
  private RiskLevelRepository riskLevelRepository;

  @Autowired
  private ObjectMapper mapper;

  @Override
  public void insertPortfolios(String portfoliosJSON) {
    try {
      Map<String, List<LinkedHashMap>> map = mapper.readValue(portfoliosJSON, Map.class);
      RiskLevelEntity riskLevelEntity = new RiskLevelEntity();

      for (var entry : map.entrySet()) {
        for (var values : entry.getValue()) {
          PortfolioEntity portfolioEntity = new PortfolioEntity();
          portfolioEntity.setTicker(values.get(TICKER).toString());
          portfolioEntity.setWeight(values.get(WEIGHT).toString());
          portfolioEntity.setRiskLevel(riskLevelEntity);
        }
        riskLevelEntity.setRiskLevelId(Integer.parseInt(entry.getKey()));
        riskLevelRepository.save(riskLevelEntity);
      }
    } catch (JsonProcessingException e) {
      log.error(e.getLocalizedMessage());
    }
  }
}
