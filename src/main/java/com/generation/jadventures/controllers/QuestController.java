package com.generation.jadventures.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generation.jadventures.model.dto.quest.QuestDtoBaseWithId;
import com.generation.jadventures.model.dto.quest.QuestDtoRpost;
import com.generation.jadventures.model.dto.quest.QuestDtoRputGuild;
import com.generation.jadventures.model.dto.quest.QuestDtoRputParty;
import com.generation.jadventures.model.dto.quest.QuestDtoWGuild;
import com.generation.jadventures.model.dtoservices.QuestConverter;
import com.generation.jadventures.model.entities.Party;
import com.generation.jadventures.model.entities.Quest;
import com.generation.jadventures.model.repositories.GuildRepository;
import com.generation.jadventures.model.repositories.PartyRepository;
import com.generation.jadventures.model.repositories.QuestRepository;

@RestController
public class QuestController {

    @Autowired
    QuestRepository qRepo;

    @Autowired
    QuestConverter qConv;

    @Autowired
    GuildRepository gRepo;

    @Autowired
    PartyRepository pRepo;

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

    @GetMapping("/quests/available")
    public ResponseEntity<?> getAvailableQuests() {

        List<QuestDtoBaseWithId> q = qRepo.findAll().stream().filter((e) -> e.getStatus().equals("AWAITING"))
                .map((e) -> qConv.QUestDtoBaseWithId(e)).toList();

        if (q != null)
            return new ResponseEntity<List<QuestDtoBaseWithId>>(q, HttpStatus.OK);
        else
            return new ResponseEntity<String>("Nessuna quest available", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/quests/byguild/{id}")
    public ResponseEntity<?> getQuestByGuildId(@PathVariable Integer id) {

        List<Quest> q = qRepo.findAll().stream().filter((p) -> p.getPatron().getId() == id).toList();

        if (q != null) {
            List<QuestDtoWGuild> quests = q.stream().map(i -> qConv.questToDtoWGuild(i)).toList();
            return new ResponseEntity<List<QuestDtoWGuild>>(quests, HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Nessuna quest presente", HttpStatus.NOT_FOUND);

    }

    // restituisce le quest con id di party
    @GetMapping("/parties/myquests/{id}")
    public ResponseEntity<?> getPartyQuests(@PathVariable Integer id) {
        List<Quest> quests = qRepo.findAll().stream().filter((q) -> q.getMyParty().getId() == id)
                .collect(Collectors.toList());
        if (quests != null) {
            List<QuestDtoBaseWithId> q = quests.stream().map(a -> qConv.QUestDtoBaseWithId(a))
                    .collect(Collectors.toList());
            return new ResponseEntity<List<QuestDtoBaseWithId>>(q, HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Nessuna quest presente", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/quests")
    public ResponseEntity<?> insertQuest(@RequestBody QuestDtoRpost dto) {

        Quest q = qConv.dtoPostToQuest(dto);
        System.out.println(dto);
        if (!possible_rank.contains(q.getQuest_rank()))
            return new ResponseEntity<String>("Hai inserito un rank non valido", HttpStatus.BAD_REQUEST);

        if (!possible_type.contains(q.getType()))
            return new ResponseEntity<String>("Hai inserito un type non valido", HttpStatus.BAD_REQUEST);

        if (!possible_status.contains(q.getStatus()))
            return new ResponseEntity<String>("Hai inserito un status non valido", HttpStatus.BAD_REQUEST);

        if (!(q.getStatus().equals("SUCCESS") || q.getStatus().equals("FAILED"))) {
            q.setDate_completed(null);
            return new ResponseEntity<Quest>(qRepo.save(q), HttpStatus.OK);
        }

        else
            return new ResponseEntity<Quest>(qRepo.save(q), HttpStatus.OK);

    }

    @PutMapping("/quests/byguild")
    public ResponseEntity<?> modifyQuestByGuild(@RequestBody QuestDtoRputGuild dto) {

        Quest q = qConv.dtoPutToQuest(dto);
        if (!possible_rank.contains(q.getQuest_rank()))
            return new ResponseEntity<String>("Hai inserito un rank non valido", HttpStatus.BAD_REQUEST);

        if (!possible_type.contains(q.getType()))
            return new ResponseEntity<String>("Hai inserito un type non valido", HttpStatus.BAD_REQUEST);

        if (!possible_status.contains(q.getStatus()))
            return new ResponseEntity<String>("Hai inserito un status non valido", HttpStatus.BAD_REQUEST);

        if (!(q.getStatus().equals("SUCCESS") || q.getStatus().equals("FAILED"))) {
            q.setDate_completed(null);
            return new ResponseEntity<Quest>(qRepo.save(q), HttpStatus.OK);
        }

        else
            return new ResponseEntity<Quest>(qRepo.save(q), HttpStatus.OK);

    }

    @PutMapping("/quests/byparty")
    public ResponseEntity<?> modifyQuestByParty(@RequestBody QuestDtoRputParty dto) {

        Quest q = qRepo.findById(dto.getId()).get();
        Party p = pRepo.findById(dto.getParty_id()).get();

        Map<String, Integer> rankToNumber = new HashMap<>();

        rankToNumber.put("S", 5);
        rankToNumber.put("A", 4);
        rankToNumber.put("B", 3);
        rankToNumber.put("C", 2);
        rankToNumber.put("D", 1);

        int rankParty = rankToNumber.get(p.getRank());
        int rankQuest = rankToNumber.get(q.getQuest_rank());

        if (rankParty >= rankQuest) {
            q.setStatus("PENDING");
            q.setMyParty(p);
            return new ResponseEntity<Quest>(qRepo.save(q), HttpStatus.OK);
        }

        else
            return new ResponseEntity<String>("Rank incompatibile", HttpStatus.BAD_REQUEST);

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
