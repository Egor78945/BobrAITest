package org.example.bobr.ai.weather.bot.service.user.log;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.bobr.ai.weather.bot.model.entity.UserLog;
import org.example.bobr.ai.weather.bot.repository.UserLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Tag(name = "Сервис логов пользователей")
@Service
public class UserLogService {
    private final UserLogRepository userLogRepository;

    public UserLogService(UserLogRepository userLogRepository) {
        this.userLogRepository = userLogRepository;
    }

    public void save(UserLog userLog) {
        userLogRepository.save(userLog);
    }

    public List<UserLog> getAll() {
        return userLogRepository.findAll();
    }

    public List<UserLog> getLogsByUserId(Long id) {
        return userLogRepository.findUserLogsByUserId(id);
    }
}
