package com.example.Runner.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @OneToMany (mappedBy = "createdBy",orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Club> clubs;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn (name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn (name = "role_id", referencedColumnName = "id")}
    )
    List<Role> roles = new ArrayList<>();
}
