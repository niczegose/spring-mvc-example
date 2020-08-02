package pl.kurs.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kurs.java.service.ExchangeRateService;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {

    private ExchangeRateService exchangeRateService = new ExchangeRateService();

    @GetMapping
    public String calcRates(@RequestParam(value = "base", required = false) String base,
                            @RequestParam(value = "target", required = false) String target,
                            @RequestParam(value = "amount", required = false) String amount,
                            ModelMap model) {
        if (base != null && target != null && amount != null) {
            model.addAttribute("message", exchangeRateService.exchange(base, target, amount));
        } else {
            model.addAttribute("message", "");
        }
        return "exchangeService";
    }
}
