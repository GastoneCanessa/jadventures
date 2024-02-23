package com.generation.jadventures.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.jadventures.model.entities.Adventurer;

public interface AdventurerRepository extends JpaRepository<Adventurer, Integer> {

}
