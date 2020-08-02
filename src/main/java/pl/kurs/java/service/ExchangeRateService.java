package pl.kurs.java.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class ExchangeRateService {
    //ERS - exhange("PLN", "EUR", 500), IvalidCurrencyException

    private Map<String, Exchanger> ex;
    private final SchedulerControl schedule;
    private final ObjectMapper objectMapper;

    public ExchangeRateService() {
        this(new ObjectMapper());
    }

    public ExchangeRateService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        ex = new HashMap<>();
        schedule = new SchedulerControl(ex);
        schedule.start();
    }

    public String exchange(String base, String target, String strAmount) {
        try {
            base = base.toUpperCase();
            target = target.toUpperCase();
            if (strAmount==null){
                throw new NumberFormatException();
            }
            double amount = Double.parseDouble(strAmount);
            return "you will get " + getExchange(base, target, amount) + " " + target + " for " + amount + " " + base;
        } catch (NumberFormatException e) {
            return "Wrong number format, try again";
        } catch (NullPointerException e) {
            return "Unsupported target currency, try again";
        } catch (IOException e) {
            return "unsupported base currency, try again";
        }
    }

    public void stop() {
        schedule.stop();
        System.out.println("that's all");
        System.out.println(ex.size());
    }

    private double getExchange(String base, String target, double amount) throws IOException {
        if (!ex.containsKey(base)) {
            ex.put(base, objectMapper.readValue(new URL("https://api.exchangeratesapi.io/latest?base=" + base), Exchanger.class));
        }
        return amount * ex.get(base).getRates().get(target);
    }

    //for testing purpose
    public int getEx() {
        return ex.size();
    }
}



