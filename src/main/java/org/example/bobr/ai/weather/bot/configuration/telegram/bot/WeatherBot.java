package org.example.bobr.ai.weather.bot.configuration.telegram.bot;

import org.example.bobr.ai.weather.bot.configuration.telegram.bot.detail.WeatherBotDetails;
import org.example.bobr.ai.weather.bot.controller.message.MessageController;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Configuration
public class WeatherBot extends TelegramLongPollingBot {
    private final WeatherBotDetails botDetails;
    private final MessageController messageController;

    public WeatherBot(WeatherBotDetails botDetails, MessageController messageController) {
        super(botDetails.getTOKEN());
        this.botDetails = botDetails;
        this.messageController = messageController;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            List<SendMessage> sendMessage = messageController.getResponse(message);
            try {
                for (SendMessage value : sendMessage)
                    sendApiMethod(value);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botDetails.getBOT_NAME();
    }
}
