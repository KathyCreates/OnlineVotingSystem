package com.voting.votingsystem.controller;

import com.voting.votingsystem.model.AppUser;
import com.voting.votingsystem.model.Candidate;
import com.voting.votingsystem.model.Vote;
import com.voting.votingsystem.repository.AppUserRepository;
import com.voting.votingsystem.repository.CandidateRepository;
import com.voting.votingsystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class VoteController {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AppUserRepository userRepository;

    // Сторінка голосування
    @GetMapping("/vote")
    public String showVotePage(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "vote";
    }

    // Обробка голосу користувача
    @PostMapping("/submit-vote")
    public String submitVote(@RequestParam("candidateId") Long candidateId,
                             Principal principal) {
        // Отримуємо користувача з бази за username
        AppUser user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Перевірка: чи вже голосував
        if (voteRepository.findByUser(user).size() > 0) {
            return "redirect:/vote?error=alreadyVoted";
        }

        // Зберігаємо голос
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid candidate ID"));

        Vote vote = new Vote(user, candidate);
        voteRepository.save(vote);

        return "redirect:/thank-you";
    }

    // Сторінка подяки після голосування
    @GetMapping("/thank-you")
    public String thankYouPage() {
        return "thank-you";
    }
}
