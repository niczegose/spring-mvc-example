package pl.kurs.java.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Exchanger {
    private String base;
    private String date;
    private Map<String, Double> rates;
}