package com.generation.jadventures.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.generation.jadventures.model.dto.guild.GuilDtoWNoQuest;
import com.generation.jadventures.model.dto.guild.GuildDtoWQuest;
import com.generation.jadventures.model.dto.login.LoginRequest;
import com.generation.jadventures.model.dtoservices.GuildConverter;
import com.generation.jadventures.model.entities.Guild;
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

    @PostMapping("/guilds/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String username = request.getName();
        String password = request.getAuthentication_seal();

        // Recupera la gilda dal repository utilizzando il nome come username
        Guild guild = gRepo.findByName(username);

        // Controlla se la gilda esiste e se il sigillo di autenticazione corrisponde alla password
        if (guild != null && guild.getAuthentication_seal().equals(password)) {
            GuilDtoWNoQuest guildDto = gConv.guildToDtoWNoQuest(guild);
            return ResponseEntity.ok(guildDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
