package com.graph.connection.repository;

import com.graph.connection.domain.ConnectionStatus;
import com.graph.connection.entity.Connection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface ConnectionRepository extends Neo4jRepository<Connection, Long> {

    @Query("""
        MATCH (pl:People)-[c:CONNECTED_TO]->(pr:People)
        WHERE id(pl) = $senderId and id(pr) = $targetUserId
        SET c.connectionType = $connectionStatus
        RETURN c
        """)
    Optional<Connection> updateStatusBySenderAndTargetId(Long senderId, Long targetUserId, ConnectionStatus connectionStatus);
}
