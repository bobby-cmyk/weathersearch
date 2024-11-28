package vttp.batch5.ssf.weatherapi_workshop.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RedirectController {

    // Define the logger
    private final Logger logger = Logger.getLogger(RedirectController.class.getName());
    
    @GetMapping("/redirect-weather")
    public String redirectWeather(
        @RequestParam String city,
        @RequestParam String units
    ) {
        logger.info("Use inputted <%s-%s>.".formatted(city, units));

        // transform all city name to lowercase
        return "redirect:/weather/" + city.toLowerCase() + "?units=" + units;
    }

    @GetMapping(path={"/weather", "/weather/"})
    public String redirectEmptyCity() {

        logger.info("City not specified. User redirected to index.");
        // transform all city name to lowercase
        return "redirect:/";
    }
}
