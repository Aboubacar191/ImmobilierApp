package oi.projet.springboot.ImmobilierApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Getter
@Setter
public class RoleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "roleEntities",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CompteUser> compteUsers;




}
