package com.graph.connection.repository;

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
            MATCH (follower:People)-[f:FOLLOWING]->(p:People)
            WHERE id(p) = $userId
            RETURN follower
            """)
    List<People> findFollowersById(Long userId);

    @Query("""
            MATCH (follower:People)-[f:FOLLOWING]->(p:People)
            WHERE id(p) = $userId
            RETURN COUNT(follower)
            """)
    Long followersCount(Long userId);

    @Query("""
            MATCH (following:People)<-[f:FOLLOWING]-(p:People)
            WHERE id(p) = $userId
            RETURN following
            """)
    List<People> findFollowingsById(Long userId);


    @Query("""
            MATCH (p:People)-[c:CONNECTED_TO {status: "CONNECTED"}]-(friend:People)
            WHERE id(p) = $userId
            RETURN friend
            """)
    List<People> findConnectionsById(Long userId);
}
