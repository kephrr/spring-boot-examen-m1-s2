package ism.spring.exmaen.gestion.employes.data.fixtures;

import ism.spring.exmaen.gestion.employes.data.entities.Departement;
import ism.spring.exmaen.gestion.employes.data.entities.Employe;
import ism.spring.exmaen.gestion.employes.data.repositories.IDepartementRepository;
import ism.spring.exmaen.gestion.employes.data.repositories.IEmployesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Order(1)
@Component
@RequiredArgsConstructor
public class EmployeFixtures implements CommandLineRunner {
    private final IEmployesRepository employesRepository;
    private final IDepartementRepository departementRepository;
    @Override
    public void run(String... args) throws Exception {
        String[] noms = {"NZE", "NDINGA", "SONKO", "SONG", "DIENG", "WEMBANYAMA"};
        String[] prenoms = {"Kephren", "Medine","Jacques","Julien","Ousmane", "Victor"};
        Integer[] salaires = {250000, 500000, 1000000, 1550000,450000, 20000000};
        String[] codes = {"FNC","INF","CMP"};

        for(int i=0; i<salaires.length; i++) {
            Optional<Departement> d = departementRepository.findById(codes[i%3]);
            Employe e = Employe.builder()
                    .nom(noms[i])
                    .prenom(prenoms[i])
                    .salaireBase(salaires[i])
                    .build();
            d.ifPresent(e::setDepartement);
            employesRepository.save(e);
        }
    }
}






