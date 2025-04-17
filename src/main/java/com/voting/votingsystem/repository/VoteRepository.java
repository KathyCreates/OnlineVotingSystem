package com.voting.votingsystem.repository;

import com.voting.votingsystem.model.Candidate;
import com.voting.votingsystem.model.AppUser;
import com.voting.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByUser(AppUser user);  // Пошук голосів за користувачем
    List<Vote> findByCandidate(Candidate candidate);  // Пошук голосів за кандидатом
    long countByCandidate(Candidate candidate);  // Підрахунок голосів для кандидата
}
