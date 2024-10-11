package org.example.bobr.ai.weather.bot.utils.weather.builder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.bobr.ai.weather.bot.configuration.weather.detail.WeatherAPIDetails;
import org.springframework.stereotype.Component;

@Tag(name = "Конструктор url", description = "Преобразует определенную информацию в корректный url для OpenWeatherMap")
@Component
public class WeatherUriBuilder {
    private final WeatherAPIDetails weatherAPIDetails;

    public WeatherUriBuilder(WeatherAPIDetails weatherAPIDetails) {
        this.weatherAPIDetails = weatherAPIDetails;
    }

    @Operation(summary = "создание url", description = "Формирует url для получения информации о погоде в городе")
    public String buildGetWeatherByCity(String city) {
        return String.format("%s?q=%s&appid=%s&units=metric", weatherAPIDetails.getBASE_URL(), city, weatherAPIDetails.getTOKEN());
    }
}
