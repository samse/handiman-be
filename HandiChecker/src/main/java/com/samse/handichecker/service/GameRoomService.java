package com.samse.handichecker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samse.handichecker.dto.GameRoomDto;
import com.samse.handichecker.entity.GameRoom;
import com.samse.handichecker.mapper.GameRoomMapper;

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
}
