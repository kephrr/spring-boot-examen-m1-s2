package ism.spring.exmaen.gestion.employes.data.fixtures;

import ism.spring.exmaen.gestion.employes.data.entities.Departement;
import ism.spring.exmaen.gestion.employes.data.repositories.IDepartementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(0)
@Component
@RequiredArgsConstructor
public class DepartementFixtures implements CommandLineRunner {
    private final IDepartementRepository departementRepository;
    @Override
    public void run(String... args) throws Exception {
        String[] codes = {"FNC","INF","CMP","COM","DRH"};
        String[] noms = {"Financier", "Informatique","Comptabilité","Marketing & communication","Ressources humaines"};

        for(int i=0; i<codes.length;i++) {
            departementRepository.save(
                    Departement.builder()
                            .codeDep(codes[i])
                            .nomDep(noms[i])
                            .build()
            );
        }
    }
}
