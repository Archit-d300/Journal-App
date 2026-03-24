package net.journalApp.service;

import net.journalApp.api_response.WeatherResponse;
import net.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
//    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String City){
        String finalAPI=appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace("City",City).replace("API_KEY",apiKey);
        if(finalAPI == null){
            return null;
        }
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse  body = response.getBody();
      return body;
    }

}
