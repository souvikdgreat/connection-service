package com.graph.connection.service;

import com.graph.connection.domain.ConnectionType;
import com.graph.connection.entity.Connection;
import com.graph.connection.entity.People;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ConnectionService {
    private final PeopleService peopleService;

    @Transactional
    public void createConnection(Long senderId, Long targetUserId) {
        People sender = peopleService.findById(senderId);
        Connection connection = Connection.builder()
                .connectionType(ConnectionType.PENDING)
                .people(peopleService.findById(targetUserId))
                .build();
        sender.getConnections().add(connection);
        peopleService.save(sender);
    }

    @Transactional
    public void followConnection(Long senderId, Long targetUserId) {
        People sender = peopleService.findById(senderId);
        sender.getFollowing().add(peopleService.findById(targetUserId));
        peopleService.save(sender);
    }

    public void updateConnection(Long targetUserId, Long senderId, Boolean isAccept) {

    }
}
