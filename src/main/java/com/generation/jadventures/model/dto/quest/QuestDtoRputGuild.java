package com.generation.jadventures.model.dto.quest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class QuestDtoRputGuild extends QuestDtoBase {

    private Integer id;
    private Integer guild_id;
}
