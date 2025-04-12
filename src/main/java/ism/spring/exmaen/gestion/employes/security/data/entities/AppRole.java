package ism.spring.exmaen.gestion.employes.security.data.entities;

import ism.spring.exmaen.gestion.employes.data.entities.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="roles")
public class AppRole  extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false,unique = true)
    private  String roleName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AppRole appRole = (AppRole) o;
        return Objects.equals(roleName, appRole.roleName);
    }

    public AppRole(String roleName) {
        this.roleName = roleName;
    }

    public AppRole(String roleName, List<AppUser> users ) {
        this.roleName = roleName;
        this.users = users;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roleName);
    }

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles")

    private List<AppUser> users ;
}
