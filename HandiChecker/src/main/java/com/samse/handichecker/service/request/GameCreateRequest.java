package com.samse.handichecker.service.request;

import java.math.BigDecimal;

import com.samse.handichecker.entity.GameRoom;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameCreateRequest {
    @NotBlank(message = "게임명은 필수입니다")
    @Size(max = 200, message = "게임명은 200자를 초과할 수 없습니다")
	private String roomName;
    
    private GameRoom.GameRule gameRule;
	
	private String handiYn;
	private BigDecimal handiRatio;
    
    @Min(value = 1, message = "최대 참여자 수는 1명 이상이어야 합니다")
    @Max(value = 100, message = "최대 참여자 수는 100명 이하여야 합니다")
	private Long maxParticipants;
    private Long currentParticipants;

    @NotBlank(message = "골프장명은 필수입니다")
    @Size(max = 200, message = "골프장명은 200자를 초과할 수 없습니다")
    private String gameLocation;
    
    @NotNull(message = "생성자 ID는 필수입니다")
    private Long createdBy;
}
