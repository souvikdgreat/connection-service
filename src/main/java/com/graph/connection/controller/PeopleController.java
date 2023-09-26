package com.graph.connection.controller;

import com.graph.connection.domain.PeopleDTO;
import com.graph.connection.service.PeopleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


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
            summary = "Search People by name",
            tags = "People"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PeopleDTO> searchPeople(@PathVariable Long id) {
        PeopleDTO people = peopleService.findById(id);
        return ResponseEntity.ok(people);
    }
}
