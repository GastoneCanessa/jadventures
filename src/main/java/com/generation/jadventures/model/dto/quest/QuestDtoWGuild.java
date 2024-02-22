package com.generation.jadventures.model.dto.quest;

import com.generation.jadventures.model.entities.Guild;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class QuestDtoWGuild extends QuestDtoBase {

    private Integer id;
    private Guild guild;
}
