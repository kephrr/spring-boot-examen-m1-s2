package ism.spring.exmaen.gestion.employes.data.repositories;

import ism.spring.exmaen.gestion.employes.data.entities.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IDepartementRepository extends JpaRepository<Departement, String> {
    List<Departement> findAllByIsActiveTrue();
    Page<Departement> findAllByIsActiveTrue(Pageable pageable);
    Optional<Departement> findByCodeDepAndIsActiveTrue(String code);
}
