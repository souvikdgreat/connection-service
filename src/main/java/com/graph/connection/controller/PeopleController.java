package com.graph.connection.controller;

import com.graph.connection.domain.ConnectionStatus;
import com.graph.connection.domain.CountDTO;
import com.graph.connection.domain.PeopleDTO;
import com.graph.connection.service.PeopleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


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
    public ResponseEntity<List<PeopleDTO>> getMutualConnections(@PathVariable Long userId, @PathVariable Long anotherUserId) {
        return ResponseEntity.ok(peopleService.findMutualConnections(userId, anotherUserId));
    }

    @Operation(
            summary = "Find Followers",
            tags = "Network"
    )
    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<PeopleDTO>> getFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(peopleService.findFollowers(userId));
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
    public ResponseEntity<List<PeopleDTO>> getFollowings(@PathVariable Long userId) {
        return ResponseEntity.ok(peopleService.findFollowings(userId));
    }

    @Operation(
            summary = "Find Connections",
            tags = "Network"
    )
    @GetMapping("/{userId}/connections")
    public ResponseEntity<List<PeopleDTO>> getConnections(@PathVariable Long userId) {
        return ResponseEntity.ok(peopleService.findConnections(userId));
    }
}
