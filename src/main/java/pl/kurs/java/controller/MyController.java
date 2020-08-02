package pl.kurs.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/my")
public class MyController {

    @GetMapping
    public String goToIndex(ModelMap model){
        model.addAttribute("message", "To jest moja zmienna");
        model.addAttribute("tasks", Arrays.asList("zadani1", "zadanie 2","zadanie 3"));
        return "index";
    }
}
