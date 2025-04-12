package ism.spring.exmaen.gestion.employes.security.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

public interface Security {
    @GetMapping("/login")
     String login(@AuthenticationPrincipal UserDetails user);

}
