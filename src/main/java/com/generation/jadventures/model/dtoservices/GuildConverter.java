package com.generation.jadventures.model.dtoservices;

import org.springframework.stereotype.Service;

import com.generation.jadventures.model.dto.guild.GuilDtoWNoQuest;
import com.generation.jadventures.model.dto.guild.GuildDtoR;
import com.generation.jadventures.model.dto.guild.GuildDtoWQuest;
import com.generation.jadventures.model.entities.Guild;

@Service
public class GuildConverter 
{
    public Guild dtoRToGuild (GuildDtoR dto)
    {
        return  Guild
                .builder()
                .name(dto.getName())
                .authentication_seal(dto.getAuthentication_seal())
                .seal_img_url(dto.getSeal_img_url())
                .hq_address(dto.getHq_address())
                .n_employees(dto.getN_employees())
                .build();
    }

    public GuildDtoWQuest guildToDtoWQuest (Guild g)
    {
        return  GuildDtoWQuest
                .builder()
                .name(g.getName())
                .authentication_seal(g.getAuthentication_seal())
                .seal_img_url(g.getSeal_img_url())
                .hq_address(g.getHq_address())
                .n_employees(g.getN_employees())
                .id(g.getId())
                .posted_quests(g.getPosted_quests())
                .build();
    }

    public GuilDtoWNoQuest guildToDtoWNoQuest (Guild g)
    {
        return  GuilDtoWNoQuest
                .builder()
                .name(g.getName())
                .authentication_seal(g.getAuthentication_seal())
                .seal_img_url(g.getSeal_img_url())
                .hq_address(g.getHq_address())
                .n_employees(g.getN_employees())
                .id(g.getId())
                .build();
    }
}
