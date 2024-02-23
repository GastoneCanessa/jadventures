package com.generation.jadventures.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.jadventures.model.entities.Party;

public interface PartyRepository extends JpaRepository<Party, Integer> 
{
    Party findByName(String name);
}
