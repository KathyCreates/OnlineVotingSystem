package com.voting.votingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard"; // шаблон у /templates/
    }

    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "user-dashboard"; // шаблон у /templates/
    }
}
