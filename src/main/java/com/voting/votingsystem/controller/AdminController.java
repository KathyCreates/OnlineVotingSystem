package com.voting.votingsystem.controller;

import com.voting.votingsystem.model.Candidate;
import com.voting.votingsystem.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/candidate/add")
    public String showAddForm(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "add-candidate";
    }

    @PostMapping("/candidate/add")
    public String addCandidate(@ModelAttribute Candidate candidate) {
        candidateRepository.save(candidate);
        return "redirect:/vote";
    }
}
