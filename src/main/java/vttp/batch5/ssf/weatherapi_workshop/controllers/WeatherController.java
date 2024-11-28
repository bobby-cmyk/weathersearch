package vttp.batch5.ssf.weatherapi_workshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import vttp.batch5.ssf.weatherapi_workshop.models.Weather;
import vttp.batch5.ssf.weatherapi_workshop.models.WeatherResponse;
import vttp.batch5.ssf.weatherapi_workshop.services.WeatherService;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    //validate to make sure not empty

    @Autowired
    private WeatherService weatherSvc;
    
    @GetMapping(path={"{city}"}, 
    produces=MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getWeather(
        @PathVariable(name="city", required=true) @NotNull @NotBlank @NotEmpty String city,
        @RequestParam(name="units", defaultValue = "metric") String units
    )
    {
        // Initialise model and view
        ModelAndView mav = new ModelAndView();

        // If random units is added, units will be in metrics
        if (!units.equals("metric") && !units.equals("imperial")) {
            units = "metric";
        }

        // Get weather from weather service by using the city params provided
        Optional<WeatherResponse> optWeatherResponse = weatherSvc.getWeather(city, units);

        if (optWeatherResponse.isEmpty()) {

            mav.setViewName("index");
            mav.addObject("errorMessage", "City does not exist");
            mav.addObject("units", units);
            mav.setStatus(HttpStatusCode.valueOf(404));

            return mav;
        }

        WeatherResponse weatherResponse = optWeatherResponse.get();

        String weatherPayload = weatherResponse.getPayload();

        Weather weather = Weather.toWeather(weatherPayload);
        
        // Set the view to weather.html
        mav.setViewName("weather");
        // Add the retrieved weather object and whether is cached
        mav.addObject("units", units);
        mav.addObject("weather", weather);
        mav.addObject("isCached", weatherResponse.isCached());
        // Set status to HTTP OK - 200
        mav.setStatus(HttpStatusCode.valueOf(200));
        
        return mav;
    }
}
