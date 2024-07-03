package com.example.Runner.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clubs")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String photoUrl;
    private String content;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn (name = "created_by",nullable = false)
    private User createdBy;

    @OneToMany (mappedBy = "club" , cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Event> events = new ArrayList<>();
}
