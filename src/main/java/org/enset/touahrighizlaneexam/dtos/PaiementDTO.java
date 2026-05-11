package org.enset.touahrighizlaneexam.dtos;

import lombok.Data;
import org.enset.touahrighizlaneexam.enums.TypePaiement;
import java.util.Date;

@Data
public class PaiementDTO {
    private Long id;
    private Date date;
    private double montant;
    private TypePaiement typePaiement;
}
