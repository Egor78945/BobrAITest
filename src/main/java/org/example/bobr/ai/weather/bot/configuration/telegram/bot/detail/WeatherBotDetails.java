package org.example.bobr.ai.weather.bot.configuration.telegram.bot.detail;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Tag(name = "Погода", description = "DTO, хранящая все детали телеграм-бота")
@Component
public class WeatherBotDetails {
    private final String BOT_NAME;
    private final String TOKEN;

    public WeatherBotDetails(@Value("${bot.name}") String BOT_NAME, @Value("${bot.token}") String TOKEN) {
        this.BOT_NAME = BOT_NAME;
        this.TOKEN = TOKEN;
    }

    public String getBOT_NAME() {
        return BOT_NAME;
    }

    public String getTOKEN() {
        return TOKEN;
    }
}
