package com.graph.connection.controller;

import com.graph.connection.service.ConnectionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connection")
@AllArgsConstructor
public class ConnectionController {
    private final ConnectionService connectionService;

    @Operation(
            summary = "Connect with People",
            tags = "Connection"
    )
    @PostMapping("/connect/{senderId}/{targetUserId}")
    public ResponseEntity<Void> createConnection(@PathVariable Long senderId, @PathVariable Long targetUserId) {
        connectionService.createConnection(senderId, targetUserId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Approve connection request",
            tags = "Connection"
    )
    @PatchMapping("/update/{targetUserId}")
    public ResponseEntity<Void> updateConnection(@PathVariable Long targetUserId, Long senderId, Boolean isAccept) {
        connectionService.updateConnection(targetUserId, senderId, isAccept);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Follow People",
            tags = "Connection"
    )
    @PostMapping("/follow/{senderId}/{targetUserId}")
    public ResponseEntity<Void> followConnection(@PathVariable Long senderId, @PathVariable Long targetUserId) {
        connectionService.followConnection(senderId, targetUserId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
