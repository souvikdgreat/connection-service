package com.graph.connection.repository;

import com.graph.connection.entity.People;
import org.springframework.data.domain.PageRequest;
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
            """)
    List<People> findMutualConnections(Long userId, Long anotherUserId);

    @Query("""
            MATCH (follower:People)-[f:FOLLOWING]->(p:People)
            WHERE id(p) = $userId
            RETURN follower SKIP $skip LIMIT $limit
            """)
    List<People> findFollowersById(Long userId, PageRequest pageable);

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

    @Query("""
            MATCH(p:People)-[c:CONNECTED_TO* {status: "CONNECTED"}]-(friend:People)
            WHERE id(p) = $userId
            AND id(p) <> id(friend)
            AND size(c) = $level
            RETURN friend
            """)
    List<People> findNthLevelConnectionById(Long userId, Long level);

    @Query("""
            MATCH (a:People)-[c:CONNECTED_TO* {status: "CONNECTED"}]-(b:People)
            WHERE ID(a) = $userId
             AND ID(b) = $anotherUserId
            RETURN min(size(c))
            """)
    Optional<Long> findNoOfLevelBetweenUser(Long userId, Long anotherUserId);
}
