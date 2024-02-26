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

import com.generation.jadventures.model.dto.login.LoginRequest;
import com.generation.jadventures.model.dto.party.PartyDtoNoAdventurers;
import com.generation.jadventures.model.dto.party.PartyDtoWFull;
import com.generation.jadventures.model.dtoservices.PartyConverter;
import com.generation.jadventures.model.entities.Party;
import com.generation.jadventures.model.repositories.PartyRepository;

@RestController
public class PartyController {

    @Autowired
    PartyRepository pRepo;

    @Autowired
    PartyConverter pConv;

    @GetMapping("/parties")
    public List<PartyDtoWFull> getQuests() {

        return pRepo.findAll().stream().map(e -> pConv.partyToDtoWFull(e)).toList();
    }

    @GetMapping("/parties/{id}")
    public PartyDtoWFull getQuest(@PathVariable Integer id) {

        return pConv.partyToDtoWFull(pRepo.findById(id).get());
    }

    // un metodo che da avventurieri con id
    @GetMapping("/parties/myadventurers/{id}")
    public PartyDtoWFull getPartyAdventurers(@PathVariable Integer id) {
        return pConv.partyToDtoWFull(pRepo.findById(id).get());
    }

    @PostMapping("/parties/login")
    public ResponseEntity<?> loginParty(@RequestBody LoginRequest request) {
        String username = request.getName();
        String password = request.getAuthentication_seal();

        Party party = pRepo.findByName(username);

        if (party != null && party.getAuthentication_seal().equals(password)) {
            PartyDtoNoAdventurers partyDto = pConv.partyToDtoWPartial(party);

            return ResponseEntity.ok(partyDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
