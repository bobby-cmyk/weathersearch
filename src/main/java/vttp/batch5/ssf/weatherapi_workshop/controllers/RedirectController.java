package vttp.batch5.ssf.weatherapi_workshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RedirectController {
    
    @GetMapping("/redirect-weather")
    public String redirectWeather(
        @RequestParam String city,
        @RequestParam String units
    ) {

        // transform all city name to lowercase
        return "redirect:/weather/" + city.toLowerCase() + "?units=" + units;
    }

    @GetMapping(path={"/weather", "/weather/"})
    public String redirectEmptyCity() {
        // transform all city name to lowercase
        return "redirect:/";
    }
}
