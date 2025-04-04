package ism.spring.exmaen.gestion.employes.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employes")
public class Employe extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long matr;
    private String nom;
    private String prenom;
    private Integer salaireBase;

    @ManyToOne
    private Departement departement;
}
