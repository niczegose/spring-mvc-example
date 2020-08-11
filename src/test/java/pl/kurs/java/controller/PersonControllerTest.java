package pl.kurs.java.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.theInstance;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldAdPeopleList() throws Exception {
        //Person testedPerson = new Person("Adam", "Zadam", "Manager", 100);

        this.mockMvc.perform(get("/people"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().attribute("message", containsString("Lista osob")))
                .andExpect(content().string(containsString("Adam")));

    }
//    @Test
//    public void shouldExchangeCurrency() throws Exception {
//        //when(service.greet()).thenReturn("Hello, Mock");
//        mockMvc.perform(
//                get("/exchange")
//                        .param("base", "EUR")
//                        .param("target", "PLN")
//                        .param("amount", "100"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(request().attribute("message", containsString("you will get")))
//                .andExpect(content().string(containsString("you will get")));
//    }
}