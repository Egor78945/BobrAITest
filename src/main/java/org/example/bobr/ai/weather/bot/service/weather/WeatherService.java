package org.example.bobr.ai.weather.bot.service.weather;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.bobr.ai.weather.bot.service.client.WebClientService;
import org.example.bobr.ai.weather.bot.utils.client.builder.HttpRequestBuilder;
import org.example.bobr.ai.weather.bot.utils.weather.builder.WeatherUriBuilder;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Tag(name = "Сервис для работы с погодой")
@Service
public class WeatherService {
    private final WebClientService webClientService;
    private final WeatherUriBuilder weatherUriBuilder;

    public WeatherService(WebClientService webClientService, WeatherUriBuilder weatherUriBuilder) {
        this.webClientService = webClientService;
        this.weatherUriBuilder = weatherUriBuilder;
    }

    @Operation(summary = "Получить погоду в городе", description = "Метод, принимающий название города и возвращающий подробную информацию о погоде в нём")
    public HttpResponse<String> getWeatherByCity(String city) {
        String uri = weatherUriBuilder.buildGetWeatherByCity(city);
        HttpResponse<String> response = webClientService.getRequest(HttpRequestBuilder.buildGet(uri));
        if(response.statusCode() == 200){
            return response;
        } else {
            throw new RuntimeException(response.body());
        }
    }
}
