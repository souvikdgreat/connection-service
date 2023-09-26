package com.graph.connection.repository;

import com.graph.connection.entity.Connection;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ConnectionRepository extends Neo4jRepository<Connection, Long> {
}
