package com.generation.jadventures.model.dto.party;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PartyDtoNoAdventurers extends PartyDtoBase
{
    private Integer id;
    private String party_rank;
}
