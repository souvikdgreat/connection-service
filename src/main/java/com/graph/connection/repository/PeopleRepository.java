package com.graph.connection.repository;

import com.graph.connection.entity.People;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PeopleRepository extends Neo4jRepository<People, Long> {
    List<People> findAllByName(String name);
}
