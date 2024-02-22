package com.generation.jadventures.model.dto.quest;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class QuestDtoBase {

    private LocalDate date_created, date_completed;
    private String status, quest_rank, area, map_url, description, type;
    private Integer reward;
}
