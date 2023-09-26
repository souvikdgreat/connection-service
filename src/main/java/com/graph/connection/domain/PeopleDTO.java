package com.graph.connection.domain;

import com.graph.connection.entity.People;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PeopleDTO {
    Long id;
    String name;

    public People createEntity() {
        return People.builder()
                .name(name)
                .build();
    }

    public static PeopleDTO from(People people) {
        return PeopleDTO.builder()
                .id(people.getId())
                .name(people.getName())
                .build();
    }
}
