package com.generation.jadventures.model.dto.guild;

import java.util.List;

import com.generation.jadventures.model.entities.Quest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GuildDtoWQuest extends GuildDtoBase {

    private Integer id;
    private List<Quest> posted_quests;
}
