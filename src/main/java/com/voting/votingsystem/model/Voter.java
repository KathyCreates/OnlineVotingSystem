package com.voting.votingsystem.model;

import jakarta.persistence.*;

@Entity
public class Voter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AppUser user;

    private boolean hasVoted;

    public Voter() {}

    public Voter(AppUser user) {
        this.user = user;
        this.hasVoted = false;
    }

    public Long getId() { return id; }

    public AppUser getUser() { return user; }
    public void setUser(AppUser user) { this.user = user; }

    public boolean isHasVoted() { return hasVoted; }
    public void setHasVoted(boolean hasVoted) { this.hasVoted = hasVoted; }
}

