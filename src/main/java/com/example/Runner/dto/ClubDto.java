package com.example.Runner.dto;

import com.example.Runner.models.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubDto {
    private Integer id;
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotEmpty(message = "PhotoUrl cannot be empty")
    private String photoUrl;
    @NotEmpty(message = "Content cannot be empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private User createdBy;
    private List<EventDto> events = new ArrayList<>();
}
