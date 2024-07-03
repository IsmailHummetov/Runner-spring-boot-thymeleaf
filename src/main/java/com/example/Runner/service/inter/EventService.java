package com.example.Runner.service.inter;

import com.example.Runner.dto.ClubDto;
import com.example.Runner.dto.EventDto;
import com.example.Runner.models.User;

import java.util.List;

public interface EventService {
    void createEvent(Integer clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    List<EventDto> searchEvents(String query);

    EventDto findByEventId(Long eventId);

    void deleteEvent(Long eventId);

    void update(EventDto eventDto);

    List<EventDto> myEvents(User user);
}
