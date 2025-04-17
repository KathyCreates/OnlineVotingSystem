package com.voting.votingsystem.controller;

import com.voting.votingsystem.model.Candidate;
import com.voting.votingsystem.repository.CandidateRepository;
import com.voting.votingsystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ResultsController {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/admin/results")
    public String viewResults(Model model) {
        // Отримуємо всіх кандидатів з бази даних
        List<Candidate> candidates = candidateRepository.findAll();
        model.addAttribute("candidates", candidates);

        // Проходимо по кандидатах і отримуємо кількість голосів для кожного
        for (Candidate candidate : candidates) {
            long votes = voteRepository.countByCandidate(candidate);
            model.addAttribute(candidate.getName(), votes);
        }

        return "view-results";  // Повертаємо сторінку для перегляду результатів
    }
}
