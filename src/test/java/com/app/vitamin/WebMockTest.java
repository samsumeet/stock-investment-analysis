package com.app.vitamin;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.app.vitamin.controller.StockInvestmentController;
import com.app.vitamin.model.PortfolioEntity;
import com.app.vitamin.service.StockInvestmentService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StockInvestmentController.class)
public class WebMockTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StockInvestmentService service;

  @Test
  public void findAllVehiclesReturnResponseFromService() throws Exception {

    when(service.getPortfolioForRisk(4))
        .thenReturn(getMockedResponse());

    this.mockMvc.perform(post("/users/me/investment-portfolio")
        .accept("*").contentType(MediaType.APPLICATION_JSON)
        .content("{\"requestLevel\":4}"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(
            "[{\"id\":6,\"ticker\":\"test\",\"weight\":\"0.4\"}]"));
  }

  public static Set<PortfolioEntity> getMockedResponse() {
    PortfolioEntity portfolioEntity = new PortfolioEntity();
    portfolioEntity.setWeight("0.4");
    portfolioEntity.setTicker("test");
    portfolioEntity.setId(6L);

    return Set.of(portfolioEntity);
  }

}
