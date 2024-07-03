package com.example.Runner.dto;

import com.example.Runner.models.Club;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Type cannot be empty")
    private String type;
    @NotEmpty(message = "PhotoUrl cannot be empty")
    private String photoUrl;
    @NotNull(message = "Starting time cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    @NotNull(message = "Finish time cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime finishTime;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Club club;
}
