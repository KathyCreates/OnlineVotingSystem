package com.voting.votingsystem.controller;

import com.voting.votingsystem.model.Candidate;
import com.voting.votingsystem.repository.CandidateRepository;
import com.voting.votingsystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ResultsController {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/admin/results")
    public String viewResults(Model model) {
        List<Candidate> candidates = candidateRepository.findAll();
        Map<String, Long> results = new HashMap<>();

        for (Candidate candidate : candidates) {
            long votes = voteRepository.countByCandidate(candidate);
            results.put(candidate.getName(), votes);
        }

        model.addAttribute("results", results);
        return "views-results";
    }
}
