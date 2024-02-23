package com.generation.jadventures.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.generation.jadventures.model.dto.party.PartyDtoWFull;
import com.generation.jadventures.model.dtoservices.PartyConverter;
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

}
