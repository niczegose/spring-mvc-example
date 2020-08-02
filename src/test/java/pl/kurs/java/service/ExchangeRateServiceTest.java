package pl.kurs.java.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ExchangeRateServiceTest {

    private ObjectMapper objectMapper;
    private ExchangeRateService underTest;
    private Map<String, Double> sampleRates;
    private Exchanger sample;

    @Before
    public void setUp() throws Exception {
        objectMapper = Mockito.mock(ObjectMapper.class);
        underTest = new ExchangeRateService(objectMapper);

        sampleRates = new HashMap<>();
        sampleRates.put("PLN", 2.0);
        sampleRates.put("USD", 1.5);
        sample = new Exchanger("USD", "2020-07-27", sampleRates);
    }

    @Test
    public void exchangeShouldReturnCorrectResult() throws IOException {
        String base = "EUR";
        String target = "USD";
        String amount = "2.0";
        String expected = "you will get 3.0 " + target + " for " + amount + " " + base;

        Mockito.when(objectMapper.readValue(new URL("https://api.exchangeratesapi.io/latest?base=" + base), Exchanger.class)).thenReturn(sample);

        assertEquals(expected, underTest.exchange(base, target, amount));
        assertEquals(expected, underTest.exchange(base, target, "2"));
        assertNotEquals(expected, underTest.exchange(base, target, "3.0"));
    }

    @Test
    public void exchangeShouldNotReturnCorrectResult() throws IOException {
        String base = "EUR";
        String target = "USD";
        String amount = "3.4";
        String expected = "you will get 3.0 " + target + " for " + amount + " " + base;

        Mockito.when(objectMapper.readValue(new URL("https://api.exchangeratesapi.io/latest?base=" + base), Exchanger.class)).thenReturn(sample);
        assertNotEquals(expected, underTest.exchange(base, target, amount));
    }

    @Test
    public void exchangeShouldReturnStringForNumberFormatException() throws IOException {
        String base = "EUR";
        String target = "USD";
        String amount = "a";
        String expected = "Wrong number format, try again";

        Mockito.when(objectMapper.readValue(new URL("https://api.exchangeratesapi.io/latest?base=" + base), Exchanger.class)).thenReturn(sample);
        assertEquals(expected, underTest.exchange(base, target, amount));
        assertEquals(expected, underTest.exchange(base, target, null));
    }

    @Test
    public void exchangeShouldReturnStringForNullPointerException() throws IOException {
        String base = "EUR";
        String target = "";
        String amount = "1";
        String expected = "Unsupported target currency, try again";

        Mockito.when(objectMapper.readValue(new URL("https://api.exchangeratesapi.io/latest?base=" + base), Exchanger.class)).thenReturn(sample);
        assertEquals(expected, underTest.exchange(base, target, amount));
        assertEquals(expected, underTest.exchange(base, null, amount));
        assertEquals(expected, underTest.exchange(null, target, amount));
    }

}