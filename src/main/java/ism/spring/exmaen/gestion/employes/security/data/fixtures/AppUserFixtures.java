package ism.spring.exmaen.gestion.employes.security.data.fixtures;


import ism.spring.exmaen.gestion.employes.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Order(4)
@RequiredArgsConstructor
public class AppUserFixtures implements CommandLineRunner {
    private  final SecurityService service;

    @Override
    public void run(String... args) throws Exception {
        String adminName = "Administrateur";
        service.addUser(adminName,"ism123");
        service.addRoleToUser(adminName,"Admin");
        service.addRoleToUser(adminName,"Client");

    }
}
