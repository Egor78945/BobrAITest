package org.example.bobr.ai.weather.bot.service.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Tag(name = "WebClient", description = "Класс, представляющий HTTP-клиента для рассылки HTTP-запросов")
@Service
public class WebClientService {
    private final HttpClient httpClient;

    public WebClientService() {
        httpClient = HttpClient.newHttpClient();
    }

    @Operation(summary = "GET-HTTP-запрос", description = "Принимает запрос и возвращающий ответ")
    public HttpResponse<String> getRequest(HttpRequest request) {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
