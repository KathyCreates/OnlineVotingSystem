package com.voting.votingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VoteController {

    // Сторінка голосування
    @GetMapping("/vote")
    public String showVotePage() {
        return "vote";  // Це має бути Thymeleaf-шаблон vote.html
    }

    // Обробка голосу користувача
    @PostMapping("/submit-vote")
    public String submitVote(@RequestParam("candidate") String candidate) {
        // Ти можеш додати логіку для збереження голосу або будь-які інші дії
        System.out.println("User voted for: " + candidate);

        // Перенаправлення на сторінку подяки
        return "redirect:/thank-you";  // Після голосування відбудеться перенаправлення
    }

    // Сторінка подяки після голосування
    @GetMapping("/thank-you")
    public String thankYouPage() {
        return "thank-you";  // Це буде Thymeleaf-шаблон thank-you.html
    }
}

