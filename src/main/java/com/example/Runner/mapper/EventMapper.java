package com.example.Runner.mapper;

import com.example.Runner.dto.EventDto;
import com.example.Runner.models.Event;

import static com.example.Runner.mapper.ClubMapper.*;

public class EventMapper {

    public static Event mapToEvent(EventDto eventDto){
        Event event = Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .startTime(eventDto.getStartTime())
                .finishTime(eventDto.getFinishTime())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .club(eventDto.getClub())
                .build();
        return event;
    }

    public static EventDto mapToEventDto(Event event){
        EventDto eventDto = EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .type(event.getType())
                .photoUrl(event.getPhotoUrl())
                .startTime(event.getStartTime())
                .finishTime(event.getFinishTime())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .club(event.getClub())
                .build();
        return eventDto;
    }
}
