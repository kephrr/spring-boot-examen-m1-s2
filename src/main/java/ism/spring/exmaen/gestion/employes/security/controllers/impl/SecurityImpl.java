package ism.spring.exmaen.gestion.employes.security.controllers.impl;

import ism.spring.exmaen.gestion.employes.security.controllers.Security;
import ism.spring.exmaen.gestion.employes.security.data.entities.AppUser;
import ism.spring.exmaen.gestion.employes.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Scanner;

@Controller
@RequiredArgsConstructor
public class SecurityImpl implements Security {
    private final SecurityService securityService;


    @Override
    public String login(UserDetails user) {
        if(user!=null){
        if(user.getAuthorities().stream().anyMatch(c->c.getAuthority().compareTo("Admin")==0)){
            return "redirect:/admin/clients";
        }
        if(user.getAuthorities().stream().anyMatch(c->c.getAuthority().compareTo("Client")==0)){
            AppUser client = securityService.getUserByUsername(user.getUsername());
            return "redirect:/client/commandes/client/"+client.getId();
        }}
        return "security/login";
    }


}
