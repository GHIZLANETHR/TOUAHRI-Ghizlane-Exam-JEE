package org.enset.touahrighizlaneexam.dtos;

import lombok.Data;
import org.enset.touahrighizlaneexam.enums.StatutContrat;
import java.util.Date;

@Data
public abstract class ContratDTO {
    private String id;
    private Date dateSouscription;
    private StatutContrat statut;
    private Date dateValidation;
    private double montantCotisation;
    private int dureeContrat;
    private double tauxCouverture;
    private ClientDTO client;
    private String type; // sera renseigné par le mapper
}
