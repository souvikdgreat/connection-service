package com.graph.connection.repository;

import com.graph.connection.domain.ConnectionStatus;
import com.graph.connection.entity.Connection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface ConnectionRepository extends Neo4jRepository<Connection, Long> {

    @Query("""
        MATCH (pl:People)-[c:CONNECTED_TO]->(pr:People)
        WHERE id(pl) = $requesterUserId and id(pr) = $userId
        SET c.status = $status
        RETURN c
        """)
    Optional<Connection> updateStatusByRequesterUserId(Long requesterUserId, Long userId, ConnectionStatus status);

    @Query("""
        MATCH (:People)-[f:FOLLOWING]->(p:People)
        WHERE id(p) = $userId
        RETURN COUNT(f)
        """)
    Long followersCount(Long userId);

    @Query("""
        MATCH (p:People)-[c:CONNECTED_TO {status: $status}]->(:People)
        WHERE id(p) = $userId
        RETURN COUNT(c)
        """)
    Long connectionsCount(Long userId, ConnectionStatus status);
}
