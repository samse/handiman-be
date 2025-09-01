package com.samse.handichecker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.samse.handichecker.entity.Member;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSearchCondition {
    
    private String nickname;
    private Member.Gender gender;
    private Double minHandicap;
    private Double maxHandicap;
    private LocalDate birthDateFrom;
    private LocalDate birthDateTo;
    private String sortBy = "created_at";
    private String sortDirection = "DESC";
    private Integer offset = 0;
    private Integer limit = 10;
    
    // 페이징을 위한 offset 계산
    public void setPageAndSize(int page, int size) {
        this.offset = page * size;
        this.limit = size;
    }
}
