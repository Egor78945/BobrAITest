package org.example.bobr.ai.weather.bot.service.user.city;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.bobr.ai.weather.bot.model.entity.UserCity;
import org.example.bobr.ai.weather.bot.repository.UserCityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Tag(name = "Сервис городов пользователей")
@Service
public class UserCityService {
    private final UserCityRepository userCityRepository;

    public UserCityService(UserCityRepository userCityRepository) {
        this.userCityRepository = userCityRepository;
    }

    public void saveUserCity(UserCity userCity){
        userCityRepository.save(userCity);
    }

    public List<String> getUsersCityByUserId(Long userId){
        return userCityRepository.getCityByUserId(userId);
    }

    public boolean existsUserCityByUserId(Long id, String city){
        return userCityRepository.existsUserCityByUserIdAndCity(id, city);
    }
}
