package pl.kurs.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/people")
public class PersonController {

    @GetMapping
    public String goToIndex(ModelMap model){
        model.addAttribute("message", "Lista osob");


        model.addAttribute("people", Arrays.asList(new Person("Adam", "Zadam", "Manager", 100),
                new Person("Ada", "Wypada", "asystent", 50)));
        return "peopleSite";
    }
}

