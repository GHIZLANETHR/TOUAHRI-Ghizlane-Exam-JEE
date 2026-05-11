package org.enset.touahrighizlaneexam.mappers;


import org.enset.touahrighizlaneexam.dtos.*;
import org.enset.touahrighizlaneexam.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AssuranceMapperImpl {

    public ClientDTO fromClient(Client client) {
        ClientDTO dto = new ClientDTO();
        BeanUtils.copyProperties(client, dto);
        return dto;
    }

    public Client toClient(ClientDTO dto) {
        Client client = new Client();
        BeanUtils.copyProperties(dto, client);
        return client;
    }

    public ContratAutomobileDTO fromContratAutomobile(ContratAutomobile contrat) {
        ContratAutomobileDTO dto = new ContratAutomobileDTO();
        BeanUtils.copyProperties(contrat, dto);
        dto.setClient(fromClient(contrat.getClient()));
        dto.setType(contrat.getClass().getSimpleName());
        return dto;
    }

    public ContratHabitationDTO fromContratHabitation(ContratHabitation contrat) {
        ContratHabitationDTO dto = new ContratHabitationDTO();
        BeanUtils.copyProperties(contrat, dto);
        dto.setClient(fromClient(contrat.getClient()));
        dto.setType(contrat.getClass().getSimpleName());
        return dto;
    }

    public ContratSanteDTO fromContratSante(ContratSante contrat) {
        ContratSanteDTO dto = new ContratSanteDTO();
        BeanUtils.copyProperties(contrat, dto);
        dto.setClient(fromClient(contrat.getClient()));
        dto.setType(contrat.getClass().getSimpleName());
        return dto;
    }

    public PaiementDTO fromPaiement(Paiement paiement) {
        PaiementDTO dto = new PaiementDTO();
        BeanUtils.copyProperties(paiement, dto);
        return dto;
    }
}