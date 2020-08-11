package pl.kurs.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.standard.processor.StandardWithTagProcessor;
import server.Equation;

@Controller
@RequestMapping("/calc")
public class CalcController {

//    @GetMapping
//    public String enter(){
//        return "calculator";
//    }

    @GetMapping
    public String processCalculation(@RequestParam(value = "number1", required = false) String number1,
                                     @RequestParam(value = "number2", required = false) String number2,
                                     @RequestParam(value = "operator", required = false) String operator,
                                     ModelMap model) {

        // for testing purpose:
        System.out.println("Values to calculate: " + number1 + " and " + number2 + " and " + operator);

        if (number1 != null && number2 != null && operator != null) {
            String message;
            if (number1.equals("")) {
                message = number2.trim();
            } else if (number2.equals("")) {
                message = number1.trim();
            } else {
                message = number1.trim() + " " + operator + " " + number2.trim();
            }

            String result = "";

            try {
                Equation.isNawias(message);
                result = Equation.obliczWyrazenie(message);
                model.addAttribute("message", "Result: " + message + " = " + result);
            } catch (Exception e) {
                model.addAttribute("message", "incorrect input data, try again");
            }
        }
        return "calculator";
    }
}
