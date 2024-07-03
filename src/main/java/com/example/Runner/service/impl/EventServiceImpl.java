package com.example.Runner.service.impl;

import com.example.Runner.dto.ClubDto;
import com.example.Runner.dto.EventDto;
import com.example.Runner.models.Club;
import com.example.Runner.models.Event;
import com.example.Runner.models.User;
import com.example.Runner.repository.ClubRepository;
import com.example.Runner.repository.EventRepository;
import com.example.Runner.service.inter.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.Runner.mapper.ClubMapper.mapToClubDto;
import static com.example.Runner.mapper.EventMapper.mapToEvent;
import static com.example.Runner.mapper.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Integer clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<EventDto> events = eventRepository.findAll().stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
        return events;
    }

    @Override
    public List<EventDto> searchEvents(String query) {
        List<EventDto> eventDtoList = eventRepository.searchEvents(query).stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
        return eventDtoList;
    }

    @Override
    public EventDto findByEventId(Long eventId) {
        EventDto eventDto = mapToEventDto(eventRepository.findById(eventId).get());
        return eventDto;
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public void update(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> myEvents(User user) {
        List<ClubDto> myClubs = user.getClubs().stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
        List<EventDto> myEvents = new ArrayList<>();
        for (ClubDto club : myClubs) {
            myEvents.addAll(club.getEvents());
        }
        return myEvents;
    }

}
