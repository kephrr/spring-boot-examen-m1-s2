package ism.spring.exmaen.gestion.employes.security.services;

import ism.spring.exmaen.gestion.employes.security.data.entities.AppRole;
import ism.spring.exmaen.gestion.employes.security.data.entities.AppUser;


public interface SecurityService {

    AppUser getUserByUsername(String username);

    AppUser addUser(String username, String password);

    AppRole addRole(String roleName);

    void addRoleToUser(String username,String roleName);
    void removeRoleToUser(String username,String roleName);
}
