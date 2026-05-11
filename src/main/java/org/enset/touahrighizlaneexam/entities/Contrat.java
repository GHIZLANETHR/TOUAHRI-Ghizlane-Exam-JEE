package org.enset.touahrighizlaneexam.entities;

import jakarta.persistence.*;
import lombok.*;
import org.enset.touahrighizlaneexam.enums.StatutContrat;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 2)
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class Contrat extends BaseEntity {
    @Id
    private String id;  // UUID
    private Date dateSouscription;
    @Enumerated(EnumType.STRING)
    private StatutContrat statut;
    private Date dateValidation;
    private double montantCotisation;
    private int dureeContrat;
    private double tauxCouverture;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "contrat", fetch = FetchType.LAZY)
    private List<Paiement> paiements;
}