package ism.spring.exmaen.gestion.employes.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IService<Type, Key> {
    List<Type> findAll();
    Page<Type> findAll(Pageable p);
    Optional<Type> findById(Key id);
    Type save(Type entity);
    int delete(Type entity);
}
