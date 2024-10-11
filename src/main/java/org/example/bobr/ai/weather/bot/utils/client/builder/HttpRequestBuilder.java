package org.example.bobr.ai.weather.bot.utils.client.builder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.net.URI;
import java.net.http.HttpRequest;

@Tag(name = "Конструктор HTTP-запросов")
public class HttpRequestBuilder {
    @Operation(summary = "Создать GET-запрос", description = "Принимает url и создаёт на его основе запрос типа GET")
    public static HttpRequest buildGet(String uri){
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
    }
}
