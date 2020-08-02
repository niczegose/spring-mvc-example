package pl.kurs.java;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kurs.java.service.ExchangeRateService;


@SpringBootTest
public class MvcExampleApplicationTest {

    //@Autowired
    //private ExchangeRateService exchangeController;

    @Test
    public void contextLoads() {
        //assertThat(exchangeController).isNotNull();
    }
}