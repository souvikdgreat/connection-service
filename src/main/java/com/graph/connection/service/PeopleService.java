package com.graph.connection.service;

import com.graph.connection.domain.ConnectionStatus;
import com.graph.connection.domain.PeopleDTO;
import com.graph.connection.entity.People;
import com.graph.connection.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleDTO createPeople(PeopleDTO peopleDTO) {
        People savedEntity = save(peopleDTO.createEntity());
        return PeopleDTO.from(savedEntity);
    }

    public PeopleDTO findById(Long id) {
        return peopleRepository.findById(id)
                .map(PeopleDTO::from)
                .orElseThrow();
    }

    public People save(People people) {
        return peopleRepository.save(people);
    }

    Optional<People> findEntityById(Long id) {
        return peopleRepository.findById(id);
    }

    public List<PeopleDTO> findMutualConnections(Long userId, Long anotherUserId) {
        return peopleRepository.findMutualConnections(userId, anotherUserId)
                .stream()
                .map(PeopleDTO::from)
                .collect(Collectors.toList());
    }

    public List<PeopleDTO> findFollowers(Long userId) {
        return peopleRepository.findFollowersById(userId)
                .stream()
                .map(PeopleDTO::from)
                .collect(Collectors.toList());
    }

    public List<PeopleDTO> findFollowings(Long userId) {
        return peopleRepository.findFollowingsById(userId)
                .stream()
                .map(PeopleDTO::from)
                .collect(Collectors.toList());
    }

    public List<PeopleDTO> findConnections(Long userId, ConnectionStatus status) {
        return peopleRepository.findConnectionsById(userId, status)
                .stream()
                .map(PeopleDTO::from)
                .collect(Collectors.toList());
    }
}