package ism.spring.exmaen.gestion.employes.services;

import ism.spring.exmaen.gestion.employes.data.entities.Departement;
import ism.spring.exmaen.gestion.employes.data.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeService extends IService<Employe, Long> {
    Page<Employe> findAllByDepartements(Pageable pageable, Departement departement);
}
