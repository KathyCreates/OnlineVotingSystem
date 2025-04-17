package com.voting.votingsystem.config; // або твій пакет

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // Отримуємо першу роль користувача
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if ("ROLE_ADMIN".equals(role)) {
            response.sendRedirect("/admin/dashboard");
        } else if ("ROLE_USER".equals(role)) {
            response.sendRedirect("/user/dashboard");
        } else {
            response.sendRedirect("/vote"); // fallback на випадок інших ролей
        }
    }
}
