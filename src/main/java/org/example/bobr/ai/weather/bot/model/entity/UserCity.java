package org.example.bobr.ai.weather.bot.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Schema(description = "DTO, представляющий сохранённые города пользователя")
@Entity
@Table(name = "user_city")
@Data
public class UserCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "city")
    private String city;

    public UserCity(Long userId, String city) {
        this.userId = userId;
        this.city = city;
    }

    public UserCity() {
    }
}
