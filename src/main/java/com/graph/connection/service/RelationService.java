package com.graph.connection.service;

import com.graph.connection.domain.ConnectionStatus;
import com.graph.connection.entity.Connection;
import com.graph.connection.entity.People;
import com.graph.connection.repository.ConnectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RelationService {
    private final PeopleService peopleService;
    private final ConnectionRepository connectionRepository;

    public void createConnection(Long userId, Long requestedUserId) {
        People sender = peopleService.findEntityById(userId).orElseThrow();
        Connection connection = Connection.builder()
                .status(ConnectionStatus.PENDING)
                .people(peopleService.findEntityById(requestedUserId).orElseThrow())
                .build();
        sender.getConnections().add(connection);
        peopleService.save(sender);
    }

    public void followConnection(Long userId, Long followingUserId) {
        People sender = peopleService.findEntityById(userId).orElseThrow();
        sender.getFollowing().add(peopleService.findEntityById(followingUserId).orElseThrow());
        peopleService.save(sender);
    }

    public void updateConnection(Long requesterUserId, Long userId, ConnectionStatus connectionStatus) {
        connectionRepository.updateStatusByRequesterUserId(requesterUserId, userId, connectionStatus)
                .orElseThrow();
    }
}
