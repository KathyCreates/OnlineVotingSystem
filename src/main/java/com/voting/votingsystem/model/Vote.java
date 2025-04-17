package com.voting.votingsystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppUser user; // Замість Voter використовуємо AppUser

    @ManyToOne
    private Candidate candidate;

    private LocalDateTime timestamp;

    public Vote() {}

    public Vote(AppUser user, Candidate candidate) {
        this.user = user;
        this.candidate = candidate;
        this.timestamp = LocalDateTime.now();  // Фіксація часу голосування
    }

    public Long getId() { return id; }

    public AppUser getUser() { return user; }
    public void setUser(AppUser user) { this.user = user; }

    public Candidate getCandidate() { return candidate; }
    public void setCandidate(Candidate candidate) { this.candidate = candidate; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
