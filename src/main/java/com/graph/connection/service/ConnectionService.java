package com.graph.connection.service;

import com.graph.connection.domain.ConnectionStatus;
import com.graph.connection.entity.Connection;
import com.graph.connection.entity.People;
import com.graph.connection.repository.ConnectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ConnectionService {
    private final PeopleService peopleService;
    private final ConnectionRepository connectionRepository;

    @Transactional
    public void createConnection(Long senderId, Long targetUserId) {
        People sender = peopleService.findEntityById(senderId).orElseThrow();
        Connection connection = Connection.builder()
                .connectionStatus(ConnectionStatus.PENDING)
                .people(peopleService.findEntityById(targetUserId).orElseThrow())
                .build();
        sender.getConnections().add(connection);
        peopleService.save(sender);
    }

    @Transactional
    public void followConnection(Long senderId, Long targetUserId) {
        People sender = peopleService.findEntityById(senderId).orElseThrow();
        sender.getFollowing().add(peopleService.findEntityById(targetUserId).orElseThrow());
        peopleService.save(sender);
    }

    public void updateConnection(Long targetUserId, Long senderId, ConnectionStatus connectionStatus) {
        connectionRepository.updateStatusBySenderAndTargetId(senderId, targetUserId, connectionStatus)
                .orElseThrow();
    }
}
