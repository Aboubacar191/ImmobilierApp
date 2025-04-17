package oi.projet.springboot.ImmobilierApp.models;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CompteUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Userid;

    @Column( nullable=false)
    private String username;

    @Column(nullable=false)
    private String Password;

    @ManyToOne
    private RoleEntity roleEntities;


    @OneToOne(mappedBy = "compteuser",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Locataire locataire;

    @ManyToOne
    private Administrateur administrateur;



}