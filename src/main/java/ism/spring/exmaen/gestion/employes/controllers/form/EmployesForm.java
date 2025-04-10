package ism.spring.exmaen.gestion.employes.controllers.form;

import ism.spring.exmaen.gestion.employes.data.entities.Employe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployesForm {
    private Long matr;
    @NotBlank(message = "Nom obligatoire")
    private String nom;
    @NotBlank(message = "Prenom obligatoire")
    private String prenom;
    @NotNull(message = "Salaire de base obligatoire")
    private Integer salaireBase;
    @NotNull
    private String codeDep;

    public Employe toEntity(){
        return Employe.builder()
                .matr(this.matr)
                .nom(this.nom)
                .prenom(this.prenom)
                .salaireBase(this.salaireBase)
                .build();
    }

    public EmployesForm toForm(Employe emp){
        return EmployesForm.builder()
                .matr(emp.getMatr())
                .nom(emp.getNom())
                .prenom(emp.getPrenom())
                .salaireBase(emp.getSalaireBase())
                .codeDep(emp.getDepartement().getCodeDep())
                .build();
    }
}




