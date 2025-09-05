package com.samse.handichecker.service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameParticipantRequest {
    @NotNull(message = "게임방 ID는 필수입니다")
	private Long roomId;
    @NotNull(message = "멤버 ID는 필수입니다")
	private Long memberId;
	private Long readyYn;
}
