package com.generation.jadventures.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;


import com.generation.jadventures.model.dto.guild.GuildDtoWQuest;

import com.generation.jadventures.model.dtoservices.GuildConverter;
import com.generation.jadventures.model.repositories.GuildRepository;

@RestController
public class GuildController {

    @Autowired
    GuildRepository gRepo;

    @Autowired
    GuildConverter gConv;

    @GetMapping("/guilds")
    public List<GuildDtoWQuest> getGuildsWithQuests() {

        return gRepo.findAll().stream().map(i -> gConv.guildToDtoWQuest(i)).toList();
    }

    @GetMapping("/guilds/{id}")
    public GuildDtoWQuest getGuild(@PathVariable Integer id) {

        return gConv.guildToDtoWQuest(gRepo.findById(id).get());
    }
}
