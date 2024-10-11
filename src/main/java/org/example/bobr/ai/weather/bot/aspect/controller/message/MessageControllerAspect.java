package org.example.bobr.ai.weather.bot.aspect.controller.message;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.bobr.ai.weather.bot.model.entity.UserLog;
import org.example.bobr.ai.weather.bot.service.user.log.UserLogService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.Timestamp;
import java.util.List;

@Tag(name = "Message", description = "Аспект, контролирующий MessageController")
@Aspect
@Component
public class MessageControllerAspect {
    private final UserLogService userLogService;

    public MessageControllerAspect(UserLogService userLogService) {
        this.userLogService = userLogService;
    }

    @Operation(summary = "Логгирование каждого вызова метода getResponse", description = "Логгирование получения пользователем данных о погоде")
    @Around("execution(* org.example.bobr.ai.weather.bot.controller.message.MessageController.getResponse(..)) && args(message)")
    public List<SendMessage> getResponseAdvice(ProceedingJoinPoint joinPoint, Message message) throws Throwable {
        List<SendMessage> o = (List<SendMessage>) joinPoint.proceed();
        for(SendMessage m: o) {
            userLogService.save(new UserLog(message.getFrom().getId(), message.getText(), new Timestamp(System.currentTimeMillis() + 10_800_000), m.getText()));
        }
        return o;
    }
}
