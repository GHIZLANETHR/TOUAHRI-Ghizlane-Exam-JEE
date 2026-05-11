package org.enset.touahrighizlaneexam.services;

import org.enset.touahrighizlaneexam.dtos.*;
import org.enset.touahrighizlaneexam.exceptions.*;

import java.util.List;

public interface AssuranceService {
    // Clients
    List<ClientDTO> listClients();
    ClientDTO getClient(Long id) throws ClientNotFoundException;
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO updateClient(Long id, ClientDTO clientDTO) throws ClientNotFoundException;
    void deleteClient(Long id);

    // Contrats
    ContratAutomobileDTO createContratAutomobile(CreateContratRequestDTO dto) throws ClientNotFoundException;
    ContratHabitationDTO createContratHabitation(CreateContratRequestDTO dto) throws ClientNotFoundException;
    ContratSanteDTO createContratSante(CreateContratRequestDTO dto) throws ClientNotFoundException;

    ContratDTO getContrat(String id) throws ContratNotFoundException;
    List<ContratDTO> listContrats();
    void updateStatut(String id, String nouveauStatut) throws ContratNotFoundException;

    // Paiements
    PaiementDTO addPaiement(PaiementRequestDTO dto) throws ContratNotFoundException;
    List<PaiementDTO> getPaiementsByContrat(String contratId, int page, int size) throws ContratNotFoundException;

    // Recherche
    List<ClientDTO> searchClients(String nom);
    List<ContratDTO> searchContrats(String keyword, String type, String statut);

    // Dashboard
    long getTotalClients();
    long getTotalContrats();
    double getTotalCotisations();
    long countContratsByStatut(String statut);
}
