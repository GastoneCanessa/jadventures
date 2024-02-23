package com.generation.jadventures.model.dto.adventurer;

import com.generation.jadventures.model.entities.Party;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AdventurerDtoWParty extends AdventurerDtoBase {

    private Party party;
}
