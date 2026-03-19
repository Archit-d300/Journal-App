package net.journalApp.service;

import net.journalApp.api_response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Weatherservice {
    private static final String apiKey="";
    private static final String API="";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String City){
        String finalAPI=API.replace("City",City).replace("API_KEY",apiKey);
          ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse  body = response.getBody();
      return body;
    }

}
