package com.samse.handichecker.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.samse.handichecker.entity.GameRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameRoomDto {
	private Long roomId;
	private String roomName;
	private GameRoom.GameRule gameRule;
	private String handiYn;
	private BigDecimal handiRatio;
	private Long maxParticipants;
	private Long currentParticipants;
	private String gameLocation;
	private Long createdBy;
	private GameRoom.RoomStatus roomStatus;
	private LocalDateTime createdAt;
	private LocalDateTime startedAt;
	private LocalDateTime finishedAt;
	
	// Entity -> DTO 변환
    public static GameRoomDto fromEntity(GameRoom gameRoom) {
        return GameRoomDto.builder()
        		.roomId(gameRoom.getRoomId())
        		.roomName(gameRoom.getRoomName())
        		.gameRule(gameRoom.getGameRule())
        		.handiYn(gameRoom.getHandiYn())
        		.handiRatio(gameRoom.getHandiRatio())
        		.maxParticipants(gameRoom.getMaxParticipants())
        		.currentParticipants(gameRoom.getCurrentParticipants())
        		.gameLocation(gameRoom.getGameLocation())
        		.createdBy(gameRoom.getCreatedBy())
        		.roomStatus(gameRoom.getRoomStatus())
        		.createdAt(gameRoom.getCreatedAt())
        		.startedAt(gameRoom.getStartedAt())
        		.finishedAt(gameRoom.getFinishedAt())
                .build();
    }
	
}
