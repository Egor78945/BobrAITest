package org.example.bobr.ai.weather.bot.configuration.telegram.bot.initializer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.example.bobr.ai.weather.bot.configuration.telegram.bot.WeatherBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Tag(name = "weather", description = "Класс, связывающий приложение и телеграм-бота")
@Component
public class WeatherBotInitializer {
    private final WeatherBot weatherBot;
    private final TelegramBotsApi telegramBotsApi;

    public WeatherBotInitializer(WeatherBot weatherBot, TelegramBotsApi telegramBotsApi) {
        this.weatherBot = weatherBot;
        this.telegramBotsApi = telegramBotsApi;
    }

    @Operation(summary = "Иницализация телеграм-бота", description = "Метод, связывающий приложение и телеграм-бота")
    @PostConstruct
    public void initBot() throws TelegramApiException {
        telegramBotsApi.registerBot(weatherBot);
    }
}
