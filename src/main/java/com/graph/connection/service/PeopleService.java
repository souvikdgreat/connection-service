package com.graph.connection.service;

import com.graph.connection.domain.PeopleDTO;
import com.graph.connection.entity.People;
import com.graph.connection.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleDTO createPeople(PeopleDTO peopleDTO) {
        People savedEntity = peopleRepository.save(peopleDTO.createEntity());
        return PeopleDTO.from(savedEntity);
    }

    public People findById(Long id) {
        return peopleRepository.findById(id)
                .orElseThrow();
    }

    public List<PeopleDTO> searchPeople(String name) {
        return peopleRepository.findAllByName(name)
                .stream()
                .map(PeopleDTO::from)
                .toList();
    }

    public void save(People people) {
        peopleRepository.save(people);
    }
}
