package org.enset.touahrighizlaneexam.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.enset.touahrighizlaneexam.enums.TypeLogement;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratHabitationDTO extends ContratDTO {
    private TypeLogement typeLogement;
    private String adresse;
    private double superficie;
}
