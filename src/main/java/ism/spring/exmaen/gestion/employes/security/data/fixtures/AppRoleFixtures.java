package ism.spring.exmaen.gestion.employes.security.data.fixtures;


import ism.spring.exmaen.gestion.employes.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Order(3)
@RequiredArgsConstructor
public class AppRoleFixtures implements CommandLineRunner {
    private  final SecurityService service;

    @Override
    public void run(String... args) throws Exception {

        service.addRole("Client");
        service.addRole("Admin");

    }
}
