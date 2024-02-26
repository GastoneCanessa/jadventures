package com.generation.jadventures.model.dtoservices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.jadventures.model.dto.party.PartyDtoNoAdventurers;
import com.generation.jadventures.model.dto.party.PartyDtoRpost;
import com.generation.jadventures.model.dto.party.PartyDtoWFull;
import com.generation.jadventures.model.dto.party.PartyDtoWithQuest;
import com.generation.jadventures.model.dto.quest.QuestDtoBase;
import com.generation.jadventures.model.entities.Party;
import com.generation.jadventures.model.repositories.AdventurerRepository;
import com.generation.jadventures.model.repositories.PartyRepository;

@Service
public class PartyConverter {

    @Autowired
    PartyRepository pRepo;

    @Autowired
    AdventurerRepository aRepo;

    @Autowired
    GuildConverter gConv;

    public Party DtoRpostToParty(PartyDtoRpost dto) {

        return Party
                .builder()
                .name(dto.getName())
                .authentication_seal(dto.getAuthentication_seal())
                .motto(dto.getMotto())
                .party_leader(dto.getParty_leader())
                .adventurers(dto.getAdventurers())
                .build();
    }

    public PartyDtoWFull partyToDtoWFull(Party p) {

        return PartyDtoWFull
                .builder()
                .id(p.getId())
                .name(p.getName())
                .authentication_seal(p.getAuthentication_seal())
                .motto(p.getMotto())
                .party_leader(p.getParty_leader())
                .adventurers(p.getAdventurers())
                .party_rank(p.getRank())
                .build();
    }

    

    public PartyDtoNoAdventurers partyToDtoWPartial(Party p) {

        return PartyDtoNoAdventurers
                .builder()
                .id(p.getId())
                .name(p.getName())
                .authentication_seal(p.getAuthentication_seal())
                .motto(p.getMotto())
                .party_rank(p.getRank())
                .build();
    }

    // public String calculatePartyRankCafone(Party p) {

    //     List<String> tuttiRank = p.getAdventurers().stream().map(a -> a.getAdventurer_rank()).toList();

    //     List<Integer> valori = new ArrayList<Integer>();

    //     for (String s : tuttiRank) {

    //         if (s.equals("S"))
    //             valori.add(5);

    //         if (s.equals("A"))
    //             valori.add(4);

    //         if (s.equals("B"))
    //             valori.add(3);

    //         if (s.equals("C"))
    //             valori.add(2);

    //         if (s.equals("D"))
    //             valori.add(1);
    //     }

    //     Collections.sort(valori, Collections.reverseOrder());

    //     int mediaprimitre = 0;
    //     for (int i = 0; i < 3; i++) {

    //         mediaprimitre += valori.get(i);
    //     }
    //     mediaprimitre = mediaprimitre / 3;

    //     int media = (mediaprimitre + valori.get(3)) / 2;

    //     Map<Integer, String> mappa = new HashMap<>();

    //     mappa.put(5, "S");
    //     mappa.put(4, "A");
    //     mappa.put(3, "B");
    //     mappa.put(2, "C");
    //     mappa.put(1, "D");

    //     String mediaRank = mappa.get(media);

    //     return mediaRank;
    // }

    // public String calculatePartyRank(Party p) {

    //     if (p.getAdventurers().size() == 0)
    //         return "D";

    //     Map<String, Integer> rankToNumber = new HashMap<>();

    //     rankToNumber.put("S", 5);
    //     rankToNumber.put("A", 4);
    //     rankToNumber.put("B", 3);
    //     rankToNumber.put("C", 2);
    //     rankToNumber.put("D", 1);

    //     List<Integer> ranks = p.getAdventurers().stream().map(a -> rankToNumber.get(a.getAdventurer_rank()))
    //             .collect(Collectors.toList());
    //     Collections.sort(ranks);

    //     int low = ranks.get(0);
    //     ranks.remove(0);
    //     if (ranks.size() == 0)
    //         return p.getAdventurers().get(0).getAdventurer_rank();

    //     int average = ranks.stream().reduce(0, (partial, el) -> partial + el) / ranks.size();
    //     int rank = (average + low) / 2;
    //     String mediaRank = rankToNumber.entrySet().stream().filter(e -> e.getValue() == rank).findFirst().get()
    //             .getKey();

    //     return mediaRank;
    // }
}
