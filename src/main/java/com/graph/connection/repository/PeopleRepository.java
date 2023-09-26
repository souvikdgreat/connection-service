package com.graph.connection.repository;

import com.graph.connection.entity.People;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PeopleRepository extends Neo4jRepository<People, Long> {
}
