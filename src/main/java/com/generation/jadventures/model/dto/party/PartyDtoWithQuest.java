package com.generation.jadventures.model.dto.party;

import java.util.Set;

import com.generation.jadventures.model.entities.Quest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PartyDtoWithQuest extends PartyDtoBase
{
    private Integer id;
    private String party_rank;
    private Set<Quest> myQuests;
}
