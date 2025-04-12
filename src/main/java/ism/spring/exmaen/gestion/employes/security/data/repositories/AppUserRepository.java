package ism.spring.exmaen.gestion.employes.security.data.repositories;


import ism.spring.exmaen.gestion.employes.security.data.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    AppUser findByLogin(String login);
}
