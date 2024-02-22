package com.generation.jadventures.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generation.jadventures.model.dto.quest.QuestDtoRpost;
import com.generation.jadventures.model.dto.quest.QuestDtoRput;
import com.generation.jadventures.model.dto.quest.QuestDtoWGuild;
import com.generation.jadventures.model.dtoservices.QuestConverter;
import com.generation.jadventures.model.entities.Quest;
import com.generation.jadventures.model.repositories.QuestRepository;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class QuestController {

    @Autowired
    QuestRepository qRepo;

    @Autowired
    QuestConverter qConv;

    @GetMapping("/quests")
    public List<QuestDtoWGuild> getQuests() {

        return qRepo.findAll().stream().map(e -> qConv.questToDtoWGuild(e)).toList();
    }

    @GetMapping("/quests/{id}")
    public QuestDtoWGuild getQuest(@PathVariable Integer id) {

        return qConv.questToDtoWGuild(qRepo.findById(id).get());
    }

    @PostMapping("/quests")
    public void insertQuest(@RequestBody QuestDtoRpost dto) {

        qRepo.save(qConv.dtoPostToQuest(dto));
    }

    @PutMapping("/quests")
    public void modifyQuest(@RequestBody QuestDtoRput dto) {

        Quest q = qConv.dtoPutToQuest(dto);

        qRepo.save(q);
    }

    @DeleteMapping("/quests/{id}")
    public void deleteQuest(@PathVariable Integer id) {

        qRepo.deleteById(id);
    }

}
