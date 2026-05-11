package org.enset.touahrighizlaneexam.entities;


import jakarta.persistence.*;
import lombok.*;
import org.enset.touahrighizlaneexam.enums.TypePaiement;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Paiement extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double montant;
    @Enumerated(EnumType.STRING)
    private TypePaiement typePaiement;

    @ManyToOne
    private Contrat contrat;
}
