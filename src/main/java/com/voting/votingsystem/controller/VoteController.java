package com.voting.votingsystem.controller;

import com.voting.votingsystem.model.AppUser;
import com.voting.votingsystem.model.Candidate;
import com.voting.votingsystem.model.Vote;
import com.voting.votingsystem.repository.CandidateRepository;
import com.voting.votingsystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VoteController {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoteRepository voteRepository;

    // Сторінка голосування
    @GetMapping("/vote")
    public String showVotePage(Model model) {
        // Отримуємо список кандидатів для відображення на сторінці голосування
        model.addAttribute("candidates", candidateRepository.findAll());
        return "vote";  // Це має бути Thymeleaf-шаблон vote.html
    }

    // Обробка голосу користувача
    @PostMapping("/submit-vote")
    public String submitVote(@RequestParam("candidateId") Long candidateId,
                             @AuthenticationPrincipal AppUser user) {
        // Перевіряємо, чи користувач вже голосував
        if (voteRepository.findByUser(user).size() > 0) {
            // Якщо голос вже є, повертаємо сторінку з помилкою
            return "redirect:/vote?error=alreadyVoted";
        }

        // Збереження голосу
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid candidate ID"));

        Vote vote = new Vote(user, candidate);
        voteRepository.save(vote);

        // Перенаправлення на сторінку подяки
        return "redirect:/thank-you";
    }

    // Сторінка подяки після голосування
    @GetMapping("/thank-you")
    public String thankYouPage() {
        return "thank-you";  // Це буде Thymeleaf-шаблон thank-you.html
    }
}
