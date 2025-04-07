package ism.spring.exmaen.gestion.employes.services.impl;

import ism.spring.exmaen.gestion.employes.data.entities.Departement;
import ism.spring.exmaen.gestion.employes.data.repositories.IDepartementRepository;
import ism.spring.exmaen.gestion.employes.services.DepartementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartementServiceImpl implements DepartementService {
    private final IDepartementRepository departementRepository;
    @Override
    public List<Departement> findAll() {
        return departementRepository.findAllByIsActiveTrue();
    }

    @Override
    public Page<Departement> findAll(Pageable p) {
        return departementRepository.findAllByIsActiveTrue(p);
    }

    @Override
    public Optional<Departement> findById(String code) {
        return departementRepository.findByCodeDepAndIsActiveTrue(code);
    }

    @Override
    public Departement save(Departement entity) {
        return departementRepository.save(entity);
    }

    @Override
    public int delete(Departement entity) {
        if(entity.getIsActive()){
            entity.setIsActive(false);
            departementRepository.save(entity);
            return 1;
        }
        return 0;
    }
}
