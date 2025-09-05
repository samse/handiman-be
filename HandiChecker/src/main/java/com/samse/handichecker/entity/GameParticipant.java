package com.samse.handichecker.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameParticipant {
	private Long participantId;
	private Long roomId;
	private Long memberId;
	private String readyYn;
	private LocalDateTime joinedAt;
	private LocalDateTime leftAt;
}
