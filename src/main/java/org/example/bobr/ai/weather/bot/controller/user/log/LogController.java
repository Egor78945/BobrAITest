package org.example.bobr.ai.weather.bot.controller.user.log;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.bobr.ai.weather.bot.model.entity.UserLog;
import org.example.bobr.ai.weather.bot.service.user.log.UserLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Контроллер логгов", description = "Контроллер, принимающий все запросы для получения данных о логах")
@RestController
@RequestMapping("/logs")
public class LogController {
    private final UserLogService userLogService;

    public LogController(UserLogService userLogService) {
        this.userLogService = userLogService;
    }

    @Operation(summary = "Получить все логи", description = "Возвращает все логи пользователей")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Найдены все логи пользователей"
            )
    })
    @GetMapping()
    public ResponseEntity<List<UserLog>> getUserLogs() {
        return ResponseEntity.ok(userLogService.getAll());
    }

    @Operation(summary = "Получить логи конкретного пользователя", description = "Возвращает все логи определённого пользователя по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Найдены все логи конкретного пользователя"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<UserLog>> getUserLogsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userLogService.getLogsByUserId(id));
    }
}
