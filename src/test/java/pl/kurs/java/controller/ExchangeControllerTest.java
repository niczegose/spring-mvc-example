package pl.kurs.java.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExchangeController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldExchangeCurrency() throws Exception {
        //when(service.greet()).thenReturn("Hello, Mock");
        mockMvc.perform(
                get("/exchange")
                        .param("base", "EUR")
                        .param("target", "PLN")
                        .param("amount", "100"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().attribute("message", containsString("you will get")))
                .andExpect(content().string(containsString("you will get")));
    }


    @Test
    public void shouldNotExchangeCurrency() throws Exception {
        //when(service.greet()).thenReturn("Hello, Mock");
        mockMvc.perform(
                get("/exchange")
                        .param("target", "PLN")
                        .param("amount", "100"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().attribute("message", equalTo("")))
                .andExpect(content().string(not(containsString("you will get"))));
    }

}