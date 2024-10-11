package org.example.bobr.ai.weather.bot.controller.message;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.bobr.ai.weather.bot.model.entity.UserCity;
import org.example.bobr.ai.weather.bot.service.user.city.UserCityService;
import org.example.bobr.ai.weather.bot.service.weather.WeatherService;
import org.example.bobr.ai.weather.bot.utils.weather.builder.WeatherInformationBuilder;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Контроллер сообщений", description = "Класс, работающий с сообщениями от пользователей телеграм-бота")
@Component
public class MessageController {
    private final WeatherService weatherService;
    private final UserCityService userCityService;

    public MessageController(WeatherService weatherService, UserCityService userCityService) {
        this.weatherService = weatherService;
        this.userCityService = userCityService;
    }

    @Operation(summary = "Обработка сообщения", description = "Метод, принимающий сообщение и решающий, какой ответ ожидает пользователь")
    public List<SendMessage> getResponse(Message message) {
        String messageText = message.getText();
        String chatId = message.getChatId().toString();

        List<SendMessage> sendMessage = new ArrayList<>();

        if (messageText.startsWith("/weather") && messageText.length() < 100) {
            if (messageText.startsWith("/weather get")) {
                List<String> citiesList = userCityService.getUsersCityByUserId(message.getFrom().getId());
                if (citiesList.isEmpty()) {
                    sendMessage.add(new SendMessage(message.getChatId().toString(), "You haven't cities in your list. To add, use - /weather add {city}"));
                }

                for (String s : citiesList) {
                    String c = s.replace(" ", "%20");
                    JsonObject information = JsonParser.parseString(weatherService.getWeatherByCity(c).body()).getAsJsonObject();
                    sendMessage.add(new SendMessage(message.getChatId().toString(), WeatherInformationBuilder.buildInformation(information, s)));
                }
                return sendMessage;
            }
            String city = extractCity(messageText);
            HttpResponse<String> response;

            try {
                response = weatherService.getWeatherByCity(new String(city.getBytes()));
            } catch (RuntimeException e) {
                sendMessage.add(new SendMessage(message.getChatId().toString(), "City not found"));
                return sendMessage;
            }

            city = city.replace("%20", " ");

            if (messageText.startsWith("/weather add")) {
                if (!userCityService.existsUserCityByUserId(message.getFrom().getId(), city)) {
                    userCityService.saveUserCity(new UserCity(message.getFrom().getId(), city));
                }
                sendMessage.add(new SendMessage(message.getChatId().toString(), "City has been saved"));
                return sendMessage;
            }

            JsonObject information = JsonParser.parseString(response.body()).getAsJsonObject();
            sendMessage.add(new SendMessage(message.getChatId().toString(), WeatherInformationBuilder.buildInformation(information, city)));
        } else {
            sendMessage.add(new SendMessage(chatId, "Unknown command"));
        }

        return sendMessage;
    }

    @Operation(summary = "Извлекание названия города", description = "Метод принимает текст сообщения пользователя и извлекает из него название города")
    private String extractCity(String messageText) {
        String[] messageArr = messageText.split(" ");
        StringBuilder cityBuilder = new StringBuilder();
        for (int i = 1; i < messageArr.length; i++) {
            if (!messageArr[i].equals("add")) {
                cityBuilder.append(messageArr[i]);
                cityBuilder.append(" ");
            }
        }
        String city = cityBuilder.toString().toLowerCase().trim();
        if (city.contains(" ")) {
            city = city.replace(" ", "%20");
        }
        return city;
    }
}
