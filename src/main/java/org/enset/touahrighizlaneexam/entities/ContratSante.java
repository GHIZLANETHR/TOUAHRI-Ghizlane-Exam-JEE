package org.enset.touahrighizlaneexam.entities;


import jakarta.persistence.*;
import lombok.*;
import org.enset.touahrighizlaneexam.enums.NiveauCouverture;

@Entity
@DiscriminatorValue("CS")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ContratSante extends Contrat {
    @Enumerated(EnumType.STRING)
    private NiveauCouverture niveauCouverture;
    private int nbPersonnesCouvertes;
}
