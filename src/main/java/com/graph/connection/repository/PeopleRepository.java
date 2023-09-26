package com.graph.connection.repository;

import com.graph.connection.entity.People;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.UUID;

public interface PeopleRepository extends Neo4jRepository<People, UUID> {
    List<People> findAllByName(String name);
}
