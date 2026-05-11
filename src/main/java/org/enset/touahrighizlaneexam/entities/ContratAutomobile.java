package org.enset.touahrighizlaneexam.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("CA")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ContratAutomobile extends Contrat {
    private String immatriculation;
    private String marque;
    private String modele;
}