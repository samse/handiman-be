package com.samse.handichecker.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.samse.handichecker.entity.GameParticipant;
import com.samse.handichecker.entity.GameRoom;


@Mapper
public interface GameRoomMapper {
    // 전체 회원 목록 조회
    List<GameRoom> selectAllGameRooms();
    // 게임 생성 
	void insertGameRoom(GameRoom room);
	// 게임방 참여여부 
	int isJoinedGameRoom(GameParticipant partcipant);
	// 게임방 참여
	void joinGameRoom(GameParticipant participant);
}
