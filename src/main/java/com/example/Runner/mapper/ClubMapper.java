package com.example.Runner.mapper;

import com.example.Runner.dto.ClubDto;
import com.example.Runner.models.Club;

import java.util.stream.Collectors;

import static com.example.Runner.mapper.EventMapper.mapToEvent;
import static com.example.Runner.mapper.EventMapper.mapToEventDto;

public class ClubMapper {
    public static ClubDto mapToClubDto(Club club) {
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .createdBy(club.getCreatedBy())
                .events(club.getEvents().stream().map(event -> mapToEventDto(event)).collect(Collectors.toList()))
                .build();
        return clubDto;
    }

    public static Club mapToClub(ClubDto clubDto){
        Club club = Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .content(clubDto.getContent())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .createdBy(clubDto.getCreatedBy())
                .events(clubDto.getEvents().stream().map(eventDto -> mapToEvent(eventDto)).collect(Collectors.toList()))
                .build();
        return club;
    }
}
