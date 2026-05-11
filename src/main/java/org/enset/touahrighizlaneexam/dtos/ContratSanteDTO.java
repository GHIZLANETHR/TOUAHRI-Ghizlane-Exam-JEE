package org.enset.touahrighizlaneexam.dtos;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.enset.touahrighizlaneexam.enums.NiveauCouverture;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratSanteDTO extends ContratDTO {
    private NiveauCouverture niveauCouverture;
    private int nbPersonnesCouvertes;
}
