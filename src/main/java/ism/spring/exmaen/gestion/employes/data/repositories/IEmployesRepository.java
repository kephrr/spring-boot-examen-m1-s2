package ism.spring.exmaen.gestion.employes.data.repositories;

import ism.spring.exmaen.gestion.employes.data.entities.Departement;
import ism.spring.exmaen.gestion.employes.data.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IEmployesRepository extends JpaRepository <Employe, Long>{
    List<Employe> findAllByIsActiveTrue();
    Page<Employe> findAllByIsActiveTrue(Pageable pageable);
    Page<Employe> findAllByIsActiveTrueAndDepartement(Pageable pageable, Departement departement);
    Optional<Employe> findByMatrAndIsActiveTrue(Long id);
    Page<Employe> findAllByIsActiveTrueAndDepartement(Departement departement, Pageable pageable);
}
