package com.allstate.service;

import com.allstate.underground.WeatherUndergroundResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DummpyServiceImpl implements DummyService{
    private static Logger log = LoggerFactory.getLogger(DummpyServiceImpl.class);
    final String API_KEY = "e0278cfc79f708bf";
    String BASE_WEATHER_URL = "http://api.wunderground.com/api/" + API_KEY + "/geolookup/conditions/q/";


    public WeatherUndergroundResponse dummyServiceMethod()
    {
        log.info("Dummy service method ");
        String weatherUrl = BASE_WEATHER_URL + "SC" + "/" + "Fort Mill".replaceAll(" ","_") + ".json";
        RestTemplate weatherTemplate = new RestTemplate();
        log.info("Reaching outside the network");
        WeatherUndergroundResponse weatherUndergroundResponse = weatherTemplate.getForObject(weatherUrl,WeatherUndergroundResponse.class);
        log.info("Response returned from external source");
        return weatherUndergroundResponse;
    }
}
