package com.app.vitamin.job;

import com.app.vitamin.service.PortfoliosService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PortfoliosInsertJob implements ApplicationListener<ApplicationReadyEvent> {

  private static final DateTimeFormatter formatter = DateTimeFormatter
      .ofPattern("dd-MM-yyyy HH:mm:ss");
  private static final String url = "https://gist.githubusercontent.com/maxkramer/d375dbc88bff45fcd84671ec1cc1e0eb/raw/1f1dd3327834ff27753df1bf589d1a861d5d4ef1/portfolios.json";

  @Autowired
  private OkHttpClient client;

  @Autowired
  private PortfoliosService portfoliosService;

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    log.info("PortfolioInsertJob started at : {}", LocalDateTime.now().format(formatter));
    Request request = new Request.Builder()
        .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .url(url)
        .build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        log.error("Unexpected code " + response);
      }

      portfoliosService.insertPortfolios(response.body().string());
      log.info("PortfolioInsertJob finished at : {}", LocalDateTime.now().format(formatter));
    } catch (
        IOException exception) {
      log.error(exception.getLocalizedMessage());
    }
  }

}