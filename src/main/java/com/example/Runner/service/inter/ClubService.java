package com.example.Runner.service.inter;

import java.util.List;
import com.example.Runner.dto.ClubDto;
import com.example.Runner.models.User;

public interface ClubService {
    List<ClubDto> findAllClub();
    void saveClub(ClubDto club);

    ClubDto findById(Integer clubId);

    void updateClub(ClubDto clubDto);

    void deleteClub(Integer clubId);
    List<ClubDto> searchClubs(String query);
    List<ClubDto> myClubs(User user);
}
