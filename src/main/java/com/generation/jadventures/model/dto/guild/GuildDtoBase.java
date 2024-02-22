package com.generation.jadventures.model.dto.guild;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GuildDtoBase {

    private String name, authentication_seal, seal_img_url, hq_address;
    private Integer n_employees;
}
