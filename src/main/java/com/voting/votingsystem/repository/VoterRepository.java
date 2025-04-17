package com.voting.votingsystem.repository;

import com.voting.votingsystem.model.Voter;
import com.voting.votingsystem.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByUser(AppUser user);
}