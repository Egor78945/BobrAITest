package org.example.bobr.ai.weather.bot.repository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.bobr.ai.weather.bot.model.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Tag(name = "Репозиторий пользовательских логгов", description = "Репозиторий для UserLog")
@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Long> {
    @Operation(summary = "Получение логгов по id пользователя", description = "Достаёт из БД объекты типа UserLog по полю user_id")
    List<UserLog> findUserLogsByUserId(Long id);
}
