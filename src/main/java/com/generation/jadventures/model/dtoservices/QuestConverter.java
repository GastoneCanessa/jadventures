package com.generation.jadventures.model.dtoservices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.jadventures.model.dto.quest.QuestDtoBaseWithId;
import com.generation.jadventures.model.dto.quest.QuestDtoRpost;
import com.generation.jadventures.model.dto.quest.QuestDtoRput;
import com.generation.jadventures.model.dto.quest.QuestDtoWGuild;
import com.generation.jadventures.model.entities.Guild;
import com.generation.jadventures.model.entities.Party;
import com.generation.jadventures.model.entities.Quest;
import com.generation.jadventures.model.repositories.GuildRepository;
import com.generation.jadventures.model.repositories.PartyRepository;

@Service
public class QuestConverter 
{
    @Autowired
    GuildRepository repo;
    @Autowired
    PartyRepository pRepo;

    public QuestDtoWGuild questToDtoWGuild (Quest q)
    {
        return QuestDtoWGuild
                .builder()
                .id(q.getId())
                .date_created(q.getDate_created())
                .date_completed(q.getDate_completed())
                .status(q.getStatus())
                .quest_rank(q.getQuest_rank())
                .area(q.getArea())
                .map_url(q.getMap_url())
                .description(q.getDescription())
                .type(q.getType())
                .reward(q.getReward())
                .guild(q.getPatron())
                .build();
    }

    public QuestDtoBaseWithId QUestDtoBaseWithId(Quest q)
    {
        return QuestDtoBaseWithId
                .builder()
                .id(q.getId())
                .date_created(q.getDate_created())
                .date_completed(q.getDate_completed())
                .status(q.getStatus())
                .quest_rank(q.getQuest_rank())
                .area(q.getArea())
                .map_url(q.getMap_url())
                .description(q.getDescription())
                .type(q.getType())
                .reward(q.getReward())
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
                .quest_rank(dto.getQuest_rank())
                .area(dto.getArea())
                .map_url(dto.getMap_url())
                .description(dto.getDescription())
                .type(dto.getType())
                .reward(dto.getReward())
                .patron(father)
                .build();
    }

    public Quest dtoPutToQuest(QuestDtoRput dto)
    {
        Guild father = null;
        Party myParty = null;
        Integer guild_id = dto.getGuild_id();
        Integer party_id = dto.getParty_id();

        if(guild_id!=null)
        {
            Optional<Guild> op = repo.findById(guild_id);

            if(op.isPresent())
                father = op.get();
        }
        if(party_id!=null)
        {
            Optional<Party> pp = pRepo.findById(party_id);

            if(pp.isPresent())
                myParty = pp.get();
        }


        return  Quest
                .builder()
                .id(dto.getId())
                .party_quests(myParty)
                .date_created(dto.getDate_created())
                .date_completed(dto.getDate_completed())
                .status(dto.getStatus())
                .quest_rank(dto.getQuest_rank())
                .area(dto.getArea())
                .map_url(dto.getMap_url())
                .description(dto.getDescription())
                .type(dto.getType())
                .reward(dto.getReward())
                .patron(father)
                .build();
    }
}
