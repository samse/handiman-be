package com.samse.handichecker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samse.handichecker.dto.ApiResponse;
import com.samse.handichecker.dto.MemberDto;
import com.samse.handichecker.dto.MemberSearchCondition;
import com.samse.handichecker.entity.Member;
import com.samse.handichecker.mapper.MemberMapper;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    
    @Autowired
    private final MemberMapper memberMapper;
    
    // 전체 회원 목록 조회
    public List<MemberDto> getAllMembers() {
        List<Member> members = memberMapper.selectAllMembers();
        return members.stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());
    }
    
    // 페이징으로 회원 목록 조회
    public ApiResponse<List<MemberDto>> getMembersWithPaging(int page, int size, String sortBy, String sortDirection) {
        MemberSearchCondition condition = MemberSearchCondition.builder()
                .sortBy(sortBy)
                .sortDirection(sortDirection)
                .build();
        condition.setPageAndSize(page, size);
        
        List<Member> members = memberMapper.selectMembersByCondition(condition);
        long totalElements = memberMapper.countMembersByCondition(condition);
        
        List<MemberDto> memberDtos = members.stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());
        
        ApiResponse.PageInfo pageInfo = ApiResponse.PageInfo.of(page, size, totalElements);
        
        return ApiResponse.success(memberDtos, pageInfo);
    }
    
    // 닉네임으로 검색
    public ApiResponse<List<MemberDto>> searchMembersByNickname(String nickname, int page, int size) {
        int offset = page * size;
        List<Member> members = memberMapper.selectMembersByNickname(nickname, offset, size);
        
        // 총 개수를 위한 조건 설정
        MemberSearchCondition countCondition = MemberSearchCondition.builder()
                .nickname(nickname)
                .build();
        long totalElements = memberMapper.countMembersByCondition(countCondition);
        
        List<MemberDto> memberDtos = members.stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());
        
        ApiResponse.PageInfo pageInfo = ApiResponse.PageInfo.of(page, size, totalElements);
        
        return ApiResponse.success(memberDtos, pageInfo);
    }
    
    // 성별로 필터링
    public List<MemberDto> getMembersByGender(Member.Gender gender, int page, int size) {
        int offset = page * size;
        List<Member> members = memberMapper.selectMembersByGender(gender, offset, size);
        
        return members.stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());
    }
    
    // 핸디캡 범위로 조회
    public List<MemberDto> getMembersByHandicapRange(Double minHandicap, Double maxHandicap) {
        List<Member> members = memberMapper.selectMembersByHandicapRange(minHandicap, maxHandicap);
        
        return members.stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());
    }
    
    // 복합 조건 검색
    public ApiResponse<List<MemberDto>> searchMembers(MemberSearchCondition condition) {
        List<Member> members = memberMapper.selectMembersByCondition(condition);
        long totalElements = memberMapper.countMembersByCondition(condition);
        
        List<MemberDto> memberDtos = members.stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());
        
        int page = condition.getOffset() / condition.getLimit();
        ApiResponse.PageInfo pageInfo = ApiResponse.PageInfo.of(page, condition.getLimit(), totalElements);
        
        return ApiResponse.success(memberDtos, pageInfo);
    }
}
