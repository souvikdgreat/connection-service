package com.graph.connection.controller;

import com.graph.connection.domain.ConnectionStatus;
import com.graph.connection.domain.CountDTO;
import com.graph.connection.domain.co.ConnectionFollowCO;
import com.graph.connection.domain.co.ConnectionRequestCO;
import com.graph.connection.domain.co.ConnectionRespondCO;
import com.graph.connection.service.ConnectionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connection")
@AllArgsConstructor
public class ConnectionController {
    private final ConnectionService connectionService;

    @Operation(
            summary = "Request a connection",
            tags = "Connection"
    )
    @PostMapping("/request")
    public ResponseEntity<Void> createConnection(@RequestBody ConnectionRequestCO requestCO) {
        connectionService.createConnection(requestCO.getUserId(), requestCO.getRequestedUserId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Operation(
            summary = "Approve or Reject connection request",
            tags = "Connection"
    )
    @PutMapping("/respond")
    public ResponseEntity<Void> updateConnection(@RequestBody ConnectionRespondCO respondCO) {
        connectionService.updateConnection(respondCO.getRequesterUserId(), respondCO.getUserId(), respondCO.getStatus());
        return ResponseEntity.ok()
                .build();
    }

    @Operation(
            summary = "Get Connection Count",
            tags = "Connection"
    )
    @GetMapping("/{userId}/count")
    public ResponseEntity<CountDTO> connectionsCount(
            @PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "CONNECTED") ConnectionStatus status) {
        CountDTO count = new CountDTO(connectionService.connectionsCount(userId, status));
        return ResponseEntity.ok(count);
    }

    @Operation(
            summary = "Follow People",
            tags = "Follow"
    )
    @PostMapping("/follow")
    public ResponseEntity<Void> followConnection(@RequestBody ConnectionFollowCO followCO) {
        connectionService.followConnection(followCO.getUserId(), followCO.getFollowingUserId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Operation(
            summary = "Get Follower Count",
            tags = "Follow"
    )
    @GetMapping("/follow/{userId}/count")
    public ResponseEntity<CountDTO> followersCount(@PathVariable Long userId) {
        CountDTO count = new CountDTO(connectionService.followersCount(userId));
        return ResponseEntity.ok(count);
    }
}
