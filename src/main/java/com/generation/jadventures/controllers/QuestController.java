package com.generation.jadventures.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public static List<String> possible_rank = Arrays.asList("S", "A", "B", "C", "D");
    public static List<String> possible_type = Arrays.asList("dungeon", "monster hunt", "village defense", "errand",
            "bodyguard", "patrol");
    public static List<String> possible_status = Arrays.asList("AWAITING", "PENDING", "SUCCESS", "FAILED");

    @GetMapping("/quests")
    public List<QuestDtoWGuild> getQuests() {

        return qRepo.findAll().stream().map(e -> qConv.questToDtoWGuild(e)).toList();
    }

    @GetMapping("/quests/{id}")
    public ResponseEntity<?> getQuest(@PathVariable Integer id) {

        Optional<Quest> oq = qRepo.findById(id);

        if (oq.isPresent()) {

            Quest q = oq.get();
            return new ResponseEntity<QuestDtoWGuild>(qConv.questToDtoWGuild(q), HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Non esiste quest con id " + id, HttpStatus.NOT_FOUND);

    }

    @PostMapping("/quests")
    public ResponseEntity<?> insertQuest(@RequestBody QuestDtoRpost dto) {

        Quest q = qConv.dtoPostToQuest(dto);
        if (!possible_rank.contains(q.getQuest_rank()))
            return new ResponseEntity<String>("Hai inserito un rank non valido", HttpStatus.BAD_REQUEST);

        if (!possible_type.contains(q.getType()))
            return new ResponseEntity<String>("Hai inserito un type non valido", HttpStatus.BAD_REQUEST);

        if (!possible_status.contains(q.getStatus()))
            return new ResponseEntity<String>("Hai inserito un status non valido", HttpStatus.BAD_REQUEST);

        if (!(q.getStatus() == "SUCCESS" || q.getStatus() == "FAILED") && q.getDate_completed() != null)
            return new ResponseEntity<String>("Non puoi mettere una data di completamento", HttpStatus.BAD_REQUEST);

        else
            return new ResponseEntity<Quest>(qRepo.save(q), HttpStatus.OK);

    }

    @PutMapping("/quests")
    public ResponseEntity<?> modifyQuest(@RequestBody QuestDtoRput dto) {

        Quest q = qConv.dtoPutToQuest(dto);
        if (!possible_rank.contains(q.getQuest_rank()))
            return new ResponseEntity<String>("Hai inserito un rank non valido", HttpStatus.BAD_REQUEST);

        if (!possible_type.contains(q.getType()))
            return new ResponseEntity<String>("Hai inserito un type non valido", HttpStatus.BAD_REQUEST);

        if (!possible_status.contains(q.getStatus()))
            return new ResponseEntity<String>("Hai inserito un status non valido", HttpStatus.BAD_REQUEST);

        if (!(q.getStatus() == "SUCCESS" || q.getStatus() == "FAILED") && q.getDate_completed() != null)
            return new ResponseEntity<String>("Non puoi mettere una data di completamento", HttpStatus.BAD_REQUEST);

        else
            return new ResponseEntity<Quest>(qRepo.save(q), HttpStatus.OK);

    }

    @DeleteMapping("/quests/{id}")
    public ResponseEntity<?> deleteQuest(@PathVariable Integer id) {

        if (qRepo.findById(id).isPresent()) {

            qRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Non esiste quest con id " + id, HttpStatus.BAD_REQUEST);
    }

}
