package com.voting.votingsystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Vote {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Voter voter;

    @ManyToOne
    private Candidate candidate;

    private LocalDateTime timestamp;

    public Vote() {}

    public Vote(Voter voter, Candidate candidate) {
        this.voter = voter;
        this.candidate = candidate;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public Voter getVoter() { return voter; }
    public void setVoter(Voter voter) { this.voter = voter; }

    public Candidate getCandidate() { return candidate; }
    public void setCandidate(Candidate candidate) { this.candidate = candidate; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}

