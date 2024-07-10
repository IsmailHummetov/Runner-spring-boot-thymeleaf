package com.example.Runner.controller;

import com.example.Runner.dto.ClubDto;
import com.example.Runner.dto.EventDto;
import com.example.Runner.models.User;
import com.example.Runner.security.SecurityUtil;
import com.example.Runner.service.inter.ClubService;
import com.example.Runner.service.inter.EventService;
import com.example.Runner.service.inter.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Event", description = "Event management APIs")
@RestController
public class EventController {
    private EventService eventService;
    private ClubService clubService;
    private UserService userService;

    @Autowired
    public EventController(EventService eventService, ClubService clubService,
                           UserService userService) {
        this.eventService = eventService;
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Integer clubId, Model model) {
        EventDto eventDto = new EventDto();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", eventDto);
        return "events-create";
    }

    @PostMapping("/events/{clubId}/new")
    public String createEvent(@PathVariable("clubId") Integer clubId,
                              Model model,
                              @Valid @ModelAttribute("event") EventDto eventDto,
                              BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "events-create";
        }
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/events")
    public String findAllEvents(Model model) {
        List<EventDto> eventDtoList = eventService.findAllEvents();
        model.addAttribute("events", eventDtoList);
        return "events-list";
    }

    @GetMapping("/myEvents")
    public String myClubs(Model model){
        User user = new User();
        if (SecurityUtil.getSessionUser()!=null)
            user=userService.findUserByUsername(SecurityUtil.getSessionUser());
        List<EventDto> myEvents =  eventService.myEvents(user);
        model.addAttribute("events",myEvents);
        return "myEvents-list";
    }

    @GetMapping("/events/search")
    public String searchEvents(@RequestParam("query") String query, Model model) {
        List<EventDto> eventDtoList = eventService.searchEvents(query);
        model.addAttribute("events", eventDtoList);
        return "events-list";
    }

    @GetMapping("/events/{eventId}")
    public String eventDetail(@PathVariable("eventId") Long eventId, Model model) {
        User user = new User();
        String username = SecurityUtil.getSessionUser();
        if(username!=null){
            user = userService.findUserByUsername(username);
        }
        EventDto eventDto = eventService.findByEventId(eventId);
        model.addAttribute("user",user);
        model.addAttribute("event", eventDto);
        return "events-detail";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findByEventId(eventId);
        model.addAttribute("event", eventDto);
        return "events-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Long eventId,
                              @Valid @ModelAttribute("event") EventDto eventDto,
                              Model model, BindingResult result) {
        EventDto event = eventService.findByEventId(eventId);
        eventDto.setClub(event.getClub());
        eventService.update(eventDto);
        return "redirect:/events";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable ("eventId") Long eventId){
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }
}
