package com.samse.handichecker.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.samse.handichecker.dto.MemberSearchCondition;
import com.samse.handichecker.entity.Member;

import java.util.List;

@Mapper
public interface MemberMapper {
    
    // 전체 회원 목록 조회
    List<Member> selectAllMembers();
    
    // 조건부 검색으로 회원 목록 조회
    List<Member> selectMembersByCondition(MemberSearchCondition condition);
    
    // 조건부 검색 총 개수
    long countMembersByCondition(MemberSearchCondition condition);
    
    // ID로 회원 조회
    Member selectMemberById(@Param("memberId") Long memberId);
    
    // 로그인 ID로 회원 조회
    Member selectMemberByLoginId(@Param("loginId") String loginId);
    
    // 닉네임으로 검색
    List<Member> selectMembersByNickname(@Param("nickname") String nickname, 
                                       @Param("offset") int offset, 
                                       @Param("limit") int limit);
    
    // 성별로 조회
    List<Member> selectMembersByGender(@Param("gender") Member.Gender gender,
                                     @Param("offset") int offset,
                                     @Param("limit") int limit);
    
    // 핸디캡 범위로 조회
    List<Member> selectMembersByHandicapRange(@Param("minHandicap") Double minHandicap,
                                            @Param("maxHandicap") Double maxHandicap);
    
    // 회원 통계 (성별별)
    List<Object> selectMemberStatsByGender();
    
    // 회원 생성
    int insertMember(Member member);
    
    // 회원 정보 수정
    int updateMember(Member member);
    
    // 회원 논리적 삭제
    int deleteMember(@Param("memberId") Long memberId);
}
