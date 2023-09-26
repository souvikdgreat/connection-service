package com.graph.connection.controller;

import com.graph.connection.domain.co.ConnectionFollowCO;
import com.graph.connection.domain.co.ConnectionRequestCO;
import com.graph.connection.domain.co.ConnectionRespondCO;
import com.graph.connection.service.RelationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connection")
@AllArgsConstructor
public class RelationController {
    private final RelationService relationService;

    @Operation(
            summary = "Request a connection",
            tags = "Connection"
    )
    @PostMapping("/request")
    public ResponseEntity<Void> createConnection(@RequestBody ConnectionRequestCO requestCO) {
        relationService.createConnection(requestCO.getUserId(), requestCO.getRequestedUserId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Operation(
            summary = "Approve or Reject connection request",
            tags = "Connection"
    )
    @PutMapping("/respond")
    public ResponseEntity<Void> updateConnection(@RequestBody ConnectionRespondCO respondCO) {
        relationService.updateConnection(respondCO.getRequesterUserId(), respondCO.getUserId(), respondCO.getStatus());
        return ResponseEntity.ok()
                .build();
    }

    @Operation(
            summary = "Follow People",
            tags = "Follow"
    )
    @PostMapping("/follow")
    public ResponseEntity<Void> followConnection(@RequestBody ConnectionFollowCO followCO) {
        relationService.followConnection(followCO.getUserId(), followCO.getFollowingUserId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
