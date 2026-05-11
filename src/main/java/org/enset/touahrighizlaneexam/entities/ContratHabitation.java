package org.enset.touahrighizlaneexam.entities;

import jakarta.persistence.*;
import lombok.*;
import org.enset.touahrighizlaneexam.enums.TypeLogement;

@Entity
@DiscriminatorValue("CH")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ContratHabitation extends Contrat {
    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement;
    private String adresse;
    private double superficie;
}
