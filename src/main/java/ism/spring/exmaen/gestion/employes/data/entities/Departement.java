package ism.spring.exmaen.gestion.employes.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "departements")
public class Departement extends AbstractEntity{
    @Id
    @Column(unique=true, nullable = false)
    private String codeDep;
    private String nomDep;

    @OneToMany(mappedBy = "departement", orphanRemoval = false)
    private List<Employe> employes;
}
