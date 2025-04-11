package ism.spring.exmaen.gestion.employes.controllers.form;

import ism.spring.exmaen.gestion.employes.data.entities.Departement;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartementsFrom {
    @NotNull(message = "Le code d'un département est obligatoire")
    private String codeDep;
    @NotNull(message = "Le code d'un département est obligatoire")
    private String nomDep;

    public Departement toEntity(){
        return Departement.builder().
                codeDep(this.codeDep).
                nomDep(this.nomDep).
                build();
    }

    public DepartementsFrom toForm(Departement dep){
        return DepartementsFrom.builder().
                codeDep(dep.getCodeDep()).
                nomDep(dep.getNomDep()).
                build();
    }
}
