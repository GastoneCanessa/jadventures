package com.generation.jadventures.model.entities;

import java.util.List;

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
}
