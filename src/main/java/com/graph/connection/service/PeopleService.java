package com.graph.connection.service;

import com.graph.connection.domain.PeopleDTO;
import com.graph.connection.entity.People;
import com.graph.connection.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
