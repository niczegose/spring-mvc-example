package pl.kurs.java.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalcController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CalcControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldAddNumbers() throws Exception {
        mockMvc.perform(
                get("/calc")
                    .param("number1", "1")
                    .param("number2", "2")
                    .param("operator", "+"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().attribute("message", containsString("1 + 2 = 3")));
    }

    @Test
    public void shouldSubtractNumbers() throws Exception {
        mockMvc.perform(
                get("/calc")
                        .param("number1", "2")
                        .param("number2", "2")
                        .param("operator", "-"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().attribute("message", containsString("2 - 2 = 0")));
    }

    @Test
    public void shouldMultiplyNumbers() throws Exception {
        mockMvc.perform(
                get("/calc")
                        .param("number1", "2")
                        .param("number2", "2")
                        .param("operator", "*"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().attribute("message", containsString("2 * 2 = 4")));
    }

    @Test
    public void shouldDivideNumbers() throws Exception {
        mockMvc.perform(
                get("/calc")
                        .param("number1", "2")
                        .param("number2", "2")
                        .param("operator", "/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().attribute("message", containsString("2 / 2 = 1")));
    }

    @Test
    public void shouldNotDivideNumbers() throws Exception {
        mockMvc.perform(
                get("/calc")
                        .param("number1", "2")
                        .param("number2", "0")
                        .param("operator", "/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().attribute("message", containsString("incorrect input")));
    }

    @Test
    public void shouldAddNumbersEnteredIntoOneParameterWithAdditionalSpace() throws Exception {
        mockMvc.perform(
                get("/calc")
                        .param("number1", "1 + 2")
                        .param("number2", "")
                        .param("operator", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().attribute("message", containsString("1 + 2 = 3")));
    }

    @Test
    public void shouldAddMultiplyNumbers() throws Exception {
        mockMvc.perform(
                get("/calc")
                        .param("number1", "1 + 2")
                        .param("number2", "2")
                        .param("operator", "+"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().attribute("message", containsString("1 + 2 + 2 = 5")));
    }

    @Test
    public void shouldAddMultiplyNumbersFromOneParamWithAdditionalOperator() throws Exception {
        mockMvc.perform(
                get("/calc")
                        .param("number1", "1 + 2")
                        .param("number2", "")
                        .param("operator", "+"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().attribute("message", containsString("1 + 2 = 3")));
    }

    @Test
    public void shouldAddMultiplyNumbersFromOneParamWithAdditionalWrongOperator() throws Exception {
        mockMvc.perform(
                get("/calc")
                        .param("number1", "")
                        .param("number2", "1 + 2")
                        .param("operator", "*"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().attribute("message", containsString("1 + 2 = 3")));
    }

    @Test
    public void shouldAddNumbersWithAdditionalSpace() throws Exception {
        mockMvc.perform(
                get("/calc")
                        .param("number1", "1 ")
                        .param("number2", "2")
                        .param("operator", "+"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().attribute("message", containsString("1 + 2 = 3")));
    }
}