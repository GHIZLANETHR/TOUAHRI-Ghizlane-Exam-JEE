package org.enset.touahrighizlaneexam.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.enset.touahrighizlaneexam.enums.NiveauCouverture;
import org.enset.touahrighizlaneexam.enums.TypeLogement;

import java.util.Date;

@Data
public class CreateContratRequestDTO {
    // Champs communs
    @NotNull
    private Long clientId;
    private Date dateSouscription;
    private double montantCotisation;
    private int dureeContrat;
    private double tauxCouverture;

    // Selon le type (renseigné via l'endpoint, pas de champ ici)
    // Auto
    private String immatriculation;
    private String marque;
    private String modele;

    // Habitation
    private TypeLogement typeLogement;
    private String adresse;
    private double superficie;

    // Santé
    private NiveauCouverture niveauCouverture;
    private int nbPersonnesCouvertes;
}
