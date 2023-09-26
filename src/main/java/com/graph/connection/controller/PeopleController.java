package com.graph.connection.controller;

import com.graph.connection.domain.ConnectionStatus;
import com.graph.connection.domain.CountDTO;
import com.graph.connection.domain.PeopleDTO;
import com.graph.connection.domain.co.Cursor;
import com.graph.connection.service.PeopleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/people")
@AllArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;

    @Operation(
            summary = "Create People",
            tags = "People"
    )
    @PostMapping
    public ResponseEntity<PeopleDTO> createPeople(@RequestBody PeopleDTO peopleDTO) {
        PeopleDTO createdResource = peopleService.createPeople(peopleDTO);
        URI location = URI.create("/people/%s".formatted(createdResource.getId()));
        return ResponseEntity.created(location)
                .body(createdResource);
    }

    @Operation(
            summary = "Search People by id",
            tags = "People"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PeopleDTO> searchPeople(@PathVariable Long id) {
        PeopleDTO people = peopleService.findById(id);
        return ResponseEntity.ok(people);
    }

    @Operation(
            summary = "Find Mutual Connections",
            tags = "Network"
    )
    @GetMapping("/{userId}/mutual/{anotherUserId}")
    public ResponseEntity<List<PeopleDTO>> getMutualConnections(
            @PathVariable Long userId,
            @PathVariable Long anotherUserId,
            Optional<Cursor> cursor) {
        return ResponseEntity.ok(peopleService.findMutualConnections(userId, anotherUserId, cursor));
    }

    @Operation(
            summary = "Find Followers",
            tags = "Network"
    )
    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<PeopleDTO>> getFollowers(
            @PathVariable Long userId,
            Optional<Cursor> cursor) {
        return ResponseEntity.ok(peopleService.findFollowers(userId, cursor));
    }

    @Operation(
            summary = "Get Follower Count",
            tags = "Network"
    )
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<CountDTO> getFollowersCount(@PathVariable Long userId) {
        CountDTO count = new CountDTO(peopleService.followersCount(userId));
        return ResponseEntity.ok(count);
    }

    @Operation(
            summary = "Find Followings",
            tags = "Network"
    )
    @GetMapping("/{userId}/followings")
    public ResponseEntity<List<PeopleDTO>> getFollowings(
            @PathVariable Long userId,
            Optional<Cursor> cursor) {
        return ResponseEntity.ok(peopleService.findFollowings(userId, cursor));
    }

    @Operation(
            summary = "Get Followings Count",
            tags = "Network"
    )
    @GetMapping("/{userId}/followings/count")
    public ResponseEntity<CountDTO> getFollowingsCount(@PathVariable Long userId) {
        CountDTO count = new CountDTO(peopleService.followingsCount(userId));
        return ResponseEntity.ok(count);
    }

    @Operation(
            summary = "Get Connection Count",
            tags = "Network"
    )
    @GetMapping("/{userId}/connections/count")
    public ResponseEntity<CountDTO> connectionsCount(
            @PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "CONNECTED") ConnectionStatus status) {
        CountDTO count = new CountDTO(peopleService.connectionsCount(userId, status));
        return ResponseEntity.ok(count);
    }

    @Operation(
            summary = "Find Connections",
            tags = "Network"
    )
    @GetMapping("/{userId}/connections")
    public ResponseEntity<List<PeopleDTO>> getConnections(
            @PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "CONNECTED") ConnectionStatus status,
            Optional<Cursor> cursor) {
        return ResponseEntity.ok(peopleService.findConnections(userId, status, cursor));
    }

    @Operation(
            summary = "Find Connections Nth Level",
            tags = "Network"
    )
    @GetMapping("/{userId}/connections/{level}")
    public ResponseEntity<List<PeopleDTO>> getNthLevelConnection(
            @PathVariable Long userId,
            @PathVariable Long level,
            Optional<Cursor> cursor) {
        return ResponseEntity.ok(peopleService.findNthLevelConnection(userId, level, cursor));
    }

    @Operation(
            summary = "Number of Level Between Users",
            tags = "Network"
    )
    @GetMapping("/{userId}/level/{anotherUserId}")
    public ResponseEntity<CountDTO> getNoOfLevelBetween(@PathVariable Long userId, @PathVariable Long anotherUserId) {
        CountDTO count = new CountDTO(peopleService.findNoOfLevelBetween(userId, anotherUserId));
        return ResponseEntity.ok(count);
    }
}
