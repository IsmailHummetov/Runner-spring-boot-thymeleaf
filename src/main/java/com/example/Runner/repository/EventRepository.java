package com.example.Runner.repository;

import com.example.Runner.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    @Query("select e from Event e where lower(e.name) like concat('%',:query,'%') ")
    List<Event> searchEvents(String query);
}
