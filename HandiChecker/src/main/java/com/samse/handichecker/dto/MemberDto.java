package com.samse.handichecker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.samse.handichecker.entity.Member;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    
    private Long memberId;
    private String loginId;
    private String nickname;
    private BigDecimal handicap;
    private LocalDate birthDate;
    private Member.Gender gender;
    private LocalDateTime createdAt;
    
    // Entity -> DTO 변환
    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .memberId(member.getMemberId())
                .loginId(member.getLoginId())
                .nickname(member.getNickname())
                .handicap(member.getHandicap())
                .birthDate(member.getBirthDate())
                .gender(member.getGender())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
