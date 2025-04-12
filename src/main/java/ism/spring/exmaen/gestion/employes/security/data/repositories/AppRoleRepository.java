package ism.spring.exmaen.gestion.employes.security.data.repositories;


import ism.spring.exmaen.gestion.employes.security.data.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
