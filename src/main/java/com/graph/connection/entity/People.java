package com.graph.connection.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class People implements PeopleProjection {

    @Id
    @GeneratedValue
    Long id;

    String name;

    @Relationship(type = "CONNECTED_TO")
    List<Connection> connections;

    @Relationship(type = "FOLLOWING")
    List<PeopleProjection> following;
}
