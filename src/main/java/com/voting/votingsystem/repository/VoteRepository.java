package com.voting.votingsystem.repository;

import com.voting.votingsystem.model.Vote;
import com.voting.votingsystem.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByCandidate(Candidate candidate); // для підрахунку голосів за кандидата
}