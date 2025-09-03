package com.samse.handichecker.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.samse.handichecker.entity.GameRoom;


@Mapper
public interface GameRoomMapper {
    // 전체 회원 목록 조회
    List<GameRoom> selectAllGameRooms();

}
