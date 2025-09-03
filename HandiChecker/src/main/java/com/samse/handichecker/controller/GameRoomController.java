package com.samse.handichecker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samse.handichecker.dto.ApiResponse;
import com.samse.handichecker.dto.GameRoomDto;
import com.samse.handichecker.service.GameRoomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/gameroom")
@RequiredArgsConstructor
public class GameRoomController {
    @Autowired
    private final GameRoomService gameRoomService;

    // 전체 게임방 목록 
    @GetMapping
    public ResponseEntity<ApiResponse<List<GameRoomDto>>> getAllRooms() {
        try {
            List<GameRoomDto> rooms = gameRoomService.getAllRooms();
            return ResponseEntity.ok(ApiResponse.success(rooms));
        } catch (Exception e) {
            log.error("게임방 목록 조회 오류", e);
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("게임방 목록 조회 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }
    
    
    
}
