package vttp.batch5.ssf.weatherapi_workshop.services;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp.batch5.ssf.weatherapi_workshop.models.WeatherResponse;
import vttp.batch5.ssf.weatherapi_workshop.repositories.WeatherRepo;

@Service
public class WeatherService {

    // Define the logger
    private static final Logger logger = Logger.getLogger(WeatherService.class.getName());

    @Value("${weatherapi.apikey}")
    private String API_KEY;

    @Autowired
    private WeatherRepo weatherRepo;
    
    public Optional<WeatherResponse> getWeather(String city, String units) {

        logger.info("Checking if %s-%s is in cache.".formatted(city, units));

        if(weatherRepo.isCached(city, units)) {

            String payload = weatherRepo.getCache(city, units);

            logger.info("Retrieved %s-%s from cache.".formatted(city, units));

            WeatherResponse weatherResponse = new WeatherResponse(true, payload);

            return Optional.of(weatherResponse);
        }
        
        logger.info("%s-%s not in cache. Building request to api.".formatted(city, units));

        String baseUrl = "https://api.openweathermap.org/data/2.5/weather";

        String fullUrl = UriComponentsBuilder
            .fromUriString(baseUrl)
            .queryParam("q", city)
            .queryParam("appid", API_KEY)
            // Change units to metrics
            .queryParam("units", units)
            .queryParam("lang", "en")
            .toUriString();

        RequestEntity<Void> req = RequestEntity
            .get(fullUrl)
            .accept(MediaType.APPLICATION_JSON)
            .build();

        try {
            // Initialise rest template
            RestTemplate template = new RestTemplate();
                
            ResponseEntity<String> resp = template.exchange(req, String.class);

            String payload = resp.getBody();

            logger.info("Successfuly exchanged for response for %s-%s.".formatted(city, units));

            weatherRepo.saveCache(city, units, payload);

            logger.info("Saved payload of %s-%s in cache with 10 minutes expiration.".formatted(city, units));

            WeatherResponse weatherResponse = new WeatherResponse(false, payload);
            
            

            // Get payload
            return Optional.of(weatherResponse);
        }

        catch (Exception e) {
            
            logger.warning(e.getMessage());

            return Optional.empty();
        }
    }   

}
