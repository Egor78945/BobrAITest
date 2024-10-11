package org.example.bobr.ai.weather.bot.configuration.telegram;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Tag(name = "Конфигурация телеграм", description = "Конфигурация для Telegram API")
@Configuration
public class TelegramConfiguration {
    @Operation(summary = "Telegram API", description = "Возвращает готовый API для телеграма")
    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(DefaultBotSession.class);
    }
}
