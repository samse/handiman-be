package com.samse.handichecker.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.samse.handichecker.entity.Member.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameRoom {

    private Long roomId;
    private String roomName;
    private GameRule gameRule;
    private String handiYn;
    private BigDecimal handiRatio;
    private Long maxParticipants;
    private Long currentParticipants;
    private String gameLocation;
    private Long createdBy;
    private RoomStatus roomStatus;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    
    public enum GameRule {
        individual, group
    }
    
    public enum RoomStatus {
    	waiting, playing, finished
    }

}
