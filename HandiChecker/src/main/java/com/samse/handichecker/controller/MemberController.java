package com.samse.handichecker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.samse.handichecker.dto.ApiResponse;
import com.samse.handichecker.dto.MemberDto;
import com.samse.handichecker.dto.MemberSearchCondition;
import com.samse.handichecker.entity.Member;
import com.samse.handichecker.service.MemberService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    private final MemberService memberService;
    
    // 전체 회원 목록 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<MemberDto>>> getAllMembers() {
        try {
            List<MemberDto> members = memberService.getAllMembers();
            return ResponseEntity.ok(ApiResponse.success(members));
        } catch (Exception e) {
            log.error("회원 목록 조회 오류", e);
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("회원 목록 조회 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }
    
    // 페이징으로 회원 목록 조회
    @GetMapping("/paged")
    public ResponseEntity<ApiResponse<List<MemberDto>>> getMembersWithPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "created_at") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection) {
        
        try {
            ApiResponse<List<MemberDto>> response = memberService.getMembersWithPaging(page, size, sortBy, sortDirection);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("페이징 조회 오류", e);
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("페이징 조회 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }
    
    // 닉네임으로 검색
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<MemberDto>>> searchMembers(
            @RequestParam String nickname,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        try {
            ApiResponse<List<MemberDto>> response = memberService.searchMembersByNickname(nickname, page, size);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("검색 오류", e);
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("검색 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }
    
    // 성별로 필터링
    @GetMapping("/gender/{gender}")
    public ResponseEntity<ApiResponse<List<MemberDto>>> getMembersByGender(
            @PathVariable Member.Gender gender,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        try {
            List<MemberDto> members = memberService.getMembersByGender(gender, page, size);
            return ResponseEntity.ok(ApiResponse.success(members));
        } catch (Exception e) {
            log.error("성별 필터링 오류", e);
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("성별 필터링 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }
    
    // 핸디캡 범위로 조회
    @GetMapping("/handicap")
    public ResponseEntity<ApiResponse<List<MemberDto>>> getMembersByHandicapRange(
            @RequestParam Double minHandicap,
            @RequestParam Double maxHandicap) {
        
        try {
            List<MemberDto> members = memberService.getMembersByHandicapRange(minHandicap, maxHandicap);
            return ResponseEntity.ok(ApiResponse.success(members));
        } catch (Exception e) {
            log.error("핸디캡 범위 조회 오류", e);
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("핸디캡 범위 조회 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }
    
    // 복합 조건 검색
    @PostMapping("/search/advanced")
    public ResponseEntity<ApiResponse<List<MemberDto>>> searchMembersAdvanced(
            @RequestBody MemberSearchCondition condition) {
        
        try {
            ApiResponse<List<MemberDto>> response = memberService.searchMembers(condition);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("복합 검색 오류", e);
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("복합 검색 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }
}
