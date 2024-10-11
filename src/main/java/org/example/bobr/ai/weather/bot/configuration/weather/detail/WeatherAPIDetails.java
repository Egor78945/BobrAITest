package org.example.bobr.ai.weather.bot.configuration.weather.detail;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Tag(name = "Детали для OpenWeatherMap", description = "DTO, хранящая все детали для OpenWeatherMap")
@Component
public class WeatherAPIDetails {
    private final String BASE_URL;
    private final String TOKEN;

    public WeatherAPIDetails(@Value("${weather.uri}") String BASE_URL, @Value("${weather.token}") String TOKEN) {
        this.BASE_URL = BASE_URL;
        this.TOKEN = TOKEN;
    }

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getTOKEN() {
        return TOKEN;
    }
}
