package ism.spring.exmaen.gestion.employes.services.impl;

import ism.spring.exmaen.gestion.employes.data.entities.Departement;
import ism.spring.exmaen.gestion.employes.data.entities.Employe;
import ism.spring.exmaen.gestion.employes.data.repositories.IDepartementRepository;
import ism.spring.exmaen.gestion.employes.data.repositories.IEmployesRepository;
import ism.spring.exmaen.gestion.employes.services.EmployeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements EmployeService {
    private final IEmployesRepository employesRepository;
    @Override
    public List<Employe> findAll() {
        return employesRepository.findAllByIsActiveTrue();
    }

    @Override
    public Page<Employe> findAll(Pageable p) {
        return employesRepository.findAllByIsActiveTrue(p);
    }

    @Override
    public Optional<Employe> findById(Long id) {
        return employesRepository.findByMatrAndIsActiveTrue(id);
    }

    @Override
    public Employe save(Employe entity) {
        return employesRepository.save(entity);
    }

    @Override
    public int delete(Employe entity) {
        if(entity.getIsActive()){
            entity.setIsActive(false);
            employesRepository.save(entity);
            return 1;
        }
        return 0;
    }

    @Override
    public Page<Employe> findAllByDepartements(Pageable pageable, Departement departement) {
        return employesRepository.findAllByIsActiveTrueAndDepartement(departement, pageable);
    }
}









