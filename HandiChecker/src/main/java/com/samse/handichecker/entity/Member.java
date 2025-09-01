package com.samse.handichecker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    
    private Long memberId;
    private String loginId;
    private String nickname;
    private BigDecimal handicap;
    private LocalDate birthDate;
    private Gender gender;
    private Boolean isDeleted;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public enum Gender {
        M, F
    }
}
