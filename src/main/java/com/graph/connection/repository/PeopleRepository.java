package com.graph.connection.repository;

import com.graph.connection.domain.ConnectionStatus;
import com.graph.connection.entity.People;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface PeopleRepository extends Neo4jRepository<People, Long> {

    @Query("""
            MATCH (a:People)-[:CONNECTED_TO{status:"CONNECTED"}]-(b:People)-[:CONNECTED_TO{status:"CONNECTED"}]-(c:People)
            WHERE ID(a) = $userId
             AND ID(c) = $anotherUserId
            RETURN b
            """)
    List<People> findMutualConnections(Long userId, Long anotherUserId);

    @Query("""
            MATCH (:People)-[f:FOLLOWING]->(p:People)
            WHERE id(p) = $userId
            RETURN p
            """)
    List<People> findFollowersById(Long userId);

    @Query("""
            MATCH (:People)<-[f:FOLLOWING]-(p:People)
            WHERE id(p) = $userId
            RETURN p
            """)
    List<People> findFollowingsById(Long userId);


    @Query("""
            MATCH (p:People)-[c:CONNECTED_TO {status: $status}]-(p1:People)
            WHERE id(p) = $userId
            RETURN p1
            """)
    List<People> findConnectionsById(Long userid, ConnectionStatus status);
}
