package org.enset.touahrighizlaneexam.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratAutomobileDTO extends ContratDTO {
    private String immatriculation;
    private String marque;
    private String modele;
}