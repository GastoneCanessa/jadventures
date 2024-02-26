package com.generation.jadventures.model.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name, authentication_seal, motto;
    
    @JsonIgnore
    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adventurer_id")
    private Adventurer party_leader;

    @JsonIgnore
    @OneToMany(mappedBy = "party",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Adventurer> adventurers;

    @JsonIgnore
    @OneToMany(mappedBy = "party_quests",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Quest> quests;

    public String getRank() {

        if (adventurers.size() == 0)
            return "D";

        Map<String, Integer> rankToNumber = new HashMap<>();

        rankToNumber.put("S", 5);
        rankToNumber.put("A", 4);
        rankToNumber.put("B", 3);
        rankToNumber.put("C", 2);
        rankToNumber.put("D", 1);

        List<Integer> ranks = adventurers.stream().map(a -> rankToNumber.get(a.getAdventurer_rank()))
                .collect(Collectors.toList());
        Collections.sort(ranks);

        int low = ranks.get(0);
        ranks.remove(0);
        if (ranks.size() == 0)
            return adventurers.get(0).getAdventurer_rank();

        int average = ranks.stream().reduce(0, (partial, el) -> partial + el) / ranks.size();
        int rank = (average + low) / 2;
        String mediaRank = rankToNumber.entrySet().stream().filter(e -> e.getValue() == rank).findFirst().get()
                .getKey();

        return mediaRank;
    }
}
