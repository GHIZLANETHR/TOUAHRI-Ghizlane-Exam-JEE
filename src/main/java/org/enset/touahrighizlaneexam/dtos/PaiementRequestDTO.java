package org.enset.touahrighizlaneexam.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.enset.touahrighizlaneexam.enums.TypePaiement;

@Data
public class PaiementRequestDTO {
    @NotNull
    private String contratId;
    private double montant;
    private TypePaiement typePaiement;
}