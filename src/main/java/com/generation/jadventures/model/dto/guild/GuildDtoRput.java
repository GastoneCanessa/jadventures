package com.generation.jadventures.model.dto.guild;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GuildDtoRput extends GuildDtoBase {

    private Integer id;
}
