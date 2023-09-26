package com.graph.connection.repository;

import com.graph.connection.domain.ConnectionStatus;
import com.graph.connection.entity.People;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface PeopleRepository extends Neo4jRepository<People, Long> {

    @Query("""
            MATCH (a:People)-[:CONNECTED_TO{status:"CONNECTED"}]-(b:People)-[:CONNECTED_TO{status:"CONNECTED"}]-(c:People)
            WHERE ID(a) = $userId
             AND ID(c) = $anotherUserId
            RETURN b
            SKIP $skip LIMIT $limit
            """)
    List<People> findMutualConnections(Long userId, Long anotherUserId, Integer skip, Integer limit);

    @Query("""
            MATCH (follower:People)-[f:FOLLOWING]->(p:People)
            WHERE id(p) = $userId
            RETURN follower
            SKIP $skip LIMIT $limit
            """)
    List<People> findFollowersById(Long userId, Integer skip, Integer limit);

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
            SKIP $skip LIMIT $limit
            """)
    List<People> findFollowingsById(Long userId, Integer skip, Integer limit);

    @Query("""
            MATCH (following:People)<-[f:FOLLOWING]-(p:People)
            WHERE id(p) = $userId
            RETURN COUNT(following)
            """)
    Long followingsCount(Long userId);

    @Query("""
        MATCH (p:People)-[c:CONNECTED_TO {status: $status}]-(:People)
        WHERE id(p) = $userId
        RETURN COUNT(c)
        """)
    Long connectionsCount(Long userId, ConnectionStatus status);

    @Query("""
            MATCH (p:People)-[c:CONNECTED_TO {status: $status}]-(friend:People)
            WHERE id(p) = $userId
            RETURN friend
            SKIP $skip LIMIT $limit
            """)
    List<People> findConnectionsById(Long userId, ConnectionStatus status, Integer skip, Integer limit);

    @Query("""
            MATCH(p:People)-[c:CONNECTED_TO* {status: "CONNECTED"}]-(friend:People)
            WHERE id(p) = $userId
            AND id(p) <> id(friend)
            AND size(c) = $level
            RETURN friend
            SKIP $skip LIMIT $limit
            """)
    List<People> findNthLevelConnectionById(Long userId, Long level, Integer skip, Integer limit);

    @Query("""
            MATCH (a:People)-[c:CONNECTED_TO* {status: "CONNECTED"}]-(b:People)
            WHERE ID(a) = $userId
             AND ID(b) = $anotherUserId
            RETURN min(size(c))
            """)
    Optional<Long> findNoOfLevelBetweenUser(Long userId, Long anotherUserId);
}
