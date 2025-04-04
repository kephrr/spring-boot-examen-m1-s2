package ism.spring.exmaen.gestion.employes.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {
    @Column(columnDefinition = "boolean default true")
    protected Boolean isActive =true;
}