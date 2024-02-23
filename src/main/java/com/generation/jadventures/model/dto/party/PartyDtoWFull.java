package com.generation.jadventures.model.dto.party;

import java.util.List;

import com.generation.jadventures.model.entities.Adventurer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PartyDtoWFull extends PartyDtoBase {

    private Integer id;
    private List<Adventurer> adventurers;
    private Adventurer party_leader;

    private String party_rank;
}
