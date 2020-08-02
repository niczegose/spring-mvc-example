package pl.kurs.java.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Person {
    private final String imie;
    private final String nazwisko;
    private final String stanowisko;
    private final int pensja;


}
