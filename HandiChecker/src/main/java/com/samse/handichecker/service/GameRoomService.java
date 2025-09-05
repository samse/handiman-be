package com.samse.handichecker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samse.handichecker.dto.GameRoomDto;
import com.samse.handichecker.entity.GameParticipant;
import com.samse.handichecker.entity.GameRoom;
import com.samse.handichecker.mapper.GameRoomMapper;
import com.samse.handichecker.service.request.GameCreateRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameRoomService {
	@Autowired
	GameRoomMapper mapper;
	
	public List<GameRoomDto> getAllRooms() {
        List<GameRoom> rooms = mapper.selectAllGameRooms();
        return rooms.stream()
                .map(GameRoomDto::fromEntity)
                .collect(Collectors.toList());
	}
	
	public GameRoom createGameRoom(GameCreateRequest request) {
		GameRoom room = new GameRoom();
		room.setRoomName(request.getRoomName());
		room.setGameRule(request.getGameRule());
		room.setHandiYn(request.getHandiYn());
		room.setHandiRatio(request.getHandiRatio());
		room.setMaxParticipants(request.getMaxParticipants());
		room.setCurrentParticipants(1L);
		room.setGameLocation(request.getGameLocation());
		room.setCreatedBy(request.getCreatedBy());
		System.out.println("GameRoomService :: createGameRoom : " + room);
		mapper.insertGameRoom(room);
		return room;
	}
	
	public boolean joinGameRoom(Long roomId, Long memberId) {
		GameParticipant participant = new GameParticipant();
		participant.setRoomId(roomId);
		participant.setMemberId(memberId);
		int joinCnt = mapper.isJoinedGameRoom(participant);
		if (joinCnt == 0) { // 미참여 상태
			log.debug("****** 미참석 상태로 참석 수행!!");
			mapper.joinGameRoom(participant);
			return true;
		}
		return false;
	}
}
