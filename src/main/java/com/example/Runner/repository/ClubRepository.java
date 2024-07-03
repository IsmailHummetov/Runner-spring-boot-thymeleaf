package com.example.Runner.repository;

import com.example.Runner.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface ClubRepository extends JpaRepository<Club,Integer> {
    @Query("SELECT c from Club c where lower(c.title) LIKE concat('%',lower(:query),'%')")
    List<Club> searchClubs(String query);
}
