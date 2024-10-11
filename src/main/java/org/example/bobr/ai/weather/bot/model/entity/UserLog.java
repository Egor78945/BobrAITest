package org.example.bobr.ai.weather.bot.model.entity;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Tag(name = "DTO, представляющий логги пользователя", description = "Entity, реализующий информацию о вызовах пользователя")
@Entity
@Table(name = "userlogs")
@Data
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "command")
    private String command;
    @Column(name = "date")
    private Timestamp date;
    @Column(name = "answer")
    private String answer;

    public UserLog(Long userId, String command, Timestamp date, String answer) {
        this.userId = userId;
        this.command = command;
        this.date = date;
        this.answer = answer;
    }

    public UserLog() {
    }
}
