package org.example.bobr.ai.weather.bot.utils.weather.builder;

import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Конструктор информации о погоде", description = "Работает с информацией о погоде и преобразует её в структурированный вид")
public class WeatherInformationBuilder {
    @Operation(summary = "преобразователь погоды", description = "Преобразует информацию о погоде в городе в структурированный вид")
    public static String buildInformation(JsonObject weatherInformation, String city){
        JsonObject mainInformation = weatherInformation.getAsJsonObject("main");

        double temperature = mainInformation.get("temp").getAsDouble();
        double feelTemperature = mainInformation.get("feels_like").getAsDouble();
        int humidity = mainInformation.get("humidity").getAsInt();

        String weatherDescription = weatherInformation.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();

        double windSpeed = weatherInformation.getAsJsonObject("wind").get("speed").getAsDouble();

        return String.format("City: %s\nTemperature: %s°C\nFeels Like: %s°C\nWeather description: %s\nHumidity: %s%%\nWind speed: %s km/h", city, temperature, feelTemperature, weatherDescription, humidity, windSpeed);
    }
}
