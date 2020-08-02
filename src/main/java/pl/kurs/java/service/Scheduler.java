package pl.kurs.java.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class Scheduler implements Runnable {
    private Map<String, Exchanger> exchangerMap;

    public Scheduler(Map<String, Exchanger> exchangerMap) {
        this.exchangerMap = exchangerMap;
    }

    @Override
    public void run() {
        exchangerMap.forEach((base, exch) -> update(base));
        //System.out.println("Map updated - " + counter);
    }

    private void update(String base) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            exchangerMap.put(base, objectMapper.readValue(new URL("https://api.exchangeratesapi.io/latest?base=" + base), Exchanger.class));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}