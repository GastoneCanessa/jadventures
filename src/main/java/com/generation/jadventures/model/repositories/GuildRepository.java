package com.generation.jadventures.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.jadventures.model.entities.Guild;

public interface GuildRepository extends JpaRepository<Guild, Integer>
{
    Guild findByName(String name);
}
