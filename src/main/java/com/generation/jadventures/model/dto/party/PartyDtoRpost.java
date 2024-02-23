package com.generation.jadventures.model.dto.party;

import java.util.List;

import com.generation.jadventures.model.entities.Adventurer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PartyDtoRpost extends PartyDtoBase {

    private Adventurer party_leader;
    private List<Adventurer> adventurers;
}
