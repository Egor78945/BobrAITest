package org.example.bobr.ai.weather.bot.repository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.bobr.ai.weather.bot.model.entity.UserCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCityRepository extends JpaRepository<UserCity, Long> {
    @Query("SELECT city FROM UserCity WHERE userId=?1")
    List<String> getCityByUserId(Long userId);
    @Operation(summary = "Проверка, сохранил ли пользователь город", description = "принимает id пользователя и название города и возвращает ответ, сохранил ли этот пользователь этот город или нет")
    @Query("SELECT CASE WHEN EXISTS (SELECT id FROM UserCity WHERE userId=?1 and city=?2) THEN CAST(1 AS BOOLEAN) ELSE CAST(0 AS BOOLEAN) END")
    boolean existsUserCityByUserIdAndCity(Long userId, String city);
}
