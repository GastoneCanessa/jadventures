package com.generation.jadventures.model.dtoservices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.jadventures.model.dto.quest.QuestDtoRpost;
import com.generation.jadventures.model.dto.quest.QuestDtoRput;
import com.generation.jadventures.model.dto.quest.QuestDtoWGuild;
import com.generation.jadventures.model.entities.Guild;
import com.generation.jadventures.model.entities.Quest;
import com.generation.jadventures.model.repositories.GuildRepository;

@Service
public class QuestConverter 
{
    @Autowired
    GuildRepository repo;

    public QuestDtoWGuild questToDtoBase (Quest q)
    {
        return QuestDtoWGuild
                .builder()
                .id(q.getId())
                .date_created(q.getDate_created())
                .date_completed(q.getDate_completed())
                .status(q.getStatus())
                .rank(q.getRank())
                .area(q.getArea())
                .map_url(q.getMap_url())
                .description(q.getDescription())
                .type(q.getType())
                .reward(q.getReward())
                .guild(q.getPatron())
                .build();
    }

    public Quest dtoPostToQuest(QuestDtoRpost dto)
    {
        Guild father = repo.findById(dto.getGuild_id()).get();

        return  Quest
                .builder()
                .date_created(dto.getDate_created())
                .date_completed(dto.getDate_completed())
                .status(dto.getStatus())
                .rank(dto.getRank())
                .area(dto.getArea())
                .map_url(dto.getMap_url())
                .description(dto.getDescription())
                .type(dto.getType())
                .reward(dto.getReward())
                .patron(father)
                .build();
    }

    public Quest dtoPutToReview(QuestDtoRput dto)
    {
        Guild father = null;
        Integer guild_id = dto.getGuild_id();

        if(guild_id!=null)
        {
            Optional<Guild> op = repo.findById(guild_id);

            if(op.isPresent())
                father = op.get();
        }

        return  Quest
                .builder()
                .id(dto.getId())
                .date_created(dto.getDate_created())
                .date_completed(dto.getDate_completed())
                .status(dto.getStatus())
                .rank(dto.getRank())
                .area(dto.getArea())
                .map_url(dto.getMap_url())
                .description(dto.getDescription())
                .type(dto.getType())
                .reward(dto.getReward())
                .patron(father)
                .build();
    }
}
