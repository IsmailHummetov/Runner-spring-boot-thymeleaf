package com.example.Runner.service.impl;

import com.example.Runner.dto.ClubDto;
import com.example.Runner.models.Club;
import com.example.Runner.models.User;
import com.example.Runner.repository.ClubRepository;
import com.example.Runner.repository.UserRepository;
import com.example.Runner.security.SecurityUtil;
import com.example.Runner.service.inter.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.Runner.mapper.ClubMapper.mapToClub;
import static com.example.Runner.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository;
    private UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository,
                           UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClubDto> findAllClub() {
        return clubRepository.findAll().stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public void saveClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        User user = userRepository.findByUsername(username);
        clubDto.setCreatedBy(user);
        clubRepository.save(mapToClub(clubDto));
    }

    @Override
    public ClubDto findById(Integer clubId) {
        return mapToClubDto(clubRepository.findById(clubId).get());
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        User user = userRepository.findByUsername(SecurityUtil.getSessionUser());
        clubDto.setCreatedBy(user);
        clubRepository.save(mapToClub(clubDto));
    }

    @Override
    public void deleteClub(Integer clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public List<ClubDto> myClubs(User user) {
        List<ClubDto> myClubs = user.getClubs().stream().map(club->mapToClubDto(club)).collect(Collectors.toList());
        return myClubs;
    }


}
