package vttp.batch5.ssf.weatherapi_workshop.repositories;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepo {
    
    @Autowired @Qualifier("redis-0")
    private RedisTemplate<String, String> template;

    // EXISTS city
    public boolean isCached(String city, String units) {

        return template.hasKey(city + units);
    }

    // SET city payload EX 600
    public void saveCache(String city, String units, String payload) {

        ValueOperations<String, String> valueOps = template.opsForValue();

        valueOps.set(city + units, payload, Duration.ofMinutes(10));
    }

    public String getCache(String city, String units) {

        ValueOperations<String, String> valueOps = template.opsForValue();

        return valueOps.get(city + units);
    }


}
