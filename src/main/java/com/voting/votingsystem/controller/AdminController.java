package com.voting.votingsystem.controller;

import com.voting.votingsystem.model.Candidate;
import com.voting.votingsystem.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/admin/candidate/add")
    public String showAddCandidateForm(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "add-candidate";
    }

    @PostMapping("/admin/candidate/add")
    public String addCandidate(@ModelAttribute("candidate") Candidate candidate) {
        candidateRepository.save(candidate); // Зберігаємо кандидата в базі
        return "redirect:/admin/dashboard"; // Після додавання перенаправляємо на панель адміністрування
    }
}

