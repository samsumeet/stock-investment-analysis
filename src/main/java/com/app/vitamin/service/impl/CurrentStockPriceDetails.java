package com.app.vitamin.service.impl;

import com.app.vitamin.exception.BadInputException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CurrentStockPriceDetails {

  private static final DateTimeFormatter formatter = DateTimeFormatter
      .ofPattern("dd-MM-yyyy HH:mm:ss");
  private String url = "https://financialmodelingprep.com/api/v3/discounted-cash-flow/%s?apikey=128ca58890841fa4e5c05d355f5b0f2d";

  @Autowired
  private OkHttpClient client;
  @Autowired
  private ObjectMapper mapper;

  public double getCurrentStockPrice(String stockName) {
    log.info("CurrentStockPrice fetching started at : {}", LocalDateTime.now().format(formatter));

    Request request = new Request.Builder()
        .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .url(String.format(this.url, stockName))
        .build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        log.error("Unexpected code " + response);
      }
      List<HashMap<String, Object>> stockList = mapper
          .readValue(response.body().string(), List.class);

      log.info("CurrentStockPrice fetching finished at : {}",
          LocalDateTime.now().format(formatter));

      return stockList.size() > 0 ? (double) stockList.get(0).get("Stock Price") : 0;

    } catch (IOException exception) {
      log.error(exception.getLocalizedMessage());
      throw new BadInputException(exception.getMessage());
    }
  }
}
