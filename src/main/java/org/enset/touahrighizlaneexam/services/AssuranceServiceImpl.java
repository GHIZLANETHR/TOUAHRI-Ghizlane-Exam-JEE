package org.enset.touahrighizlaneexam.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.enset.touahrighizlaneexam.dtos.*;
import org.enset.touahrighizlaneexam.entities.*;
import org.enset.touahrighizlaneexam.enums.StatutContrat;
import org.enset.touahrighizlaneexam.enums.TypePaiement;
import org.enset.touahrighizlaneexam.exceptions.*;
import org.enset.touahrighizlaneexam.mappers.AssuranceMapperImpl;
import org.enset.touahrighizlaneexam.repositories.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AssuranceServiceImpl implements AssuranceService {

    private ClientRepository clientRepository;
    private ContratRepository contratRepository;
    private PaiementRepository paiementRepository;
    private AssuranceMapperImpl mapper;

    // Clients
    @Override
    public List<ClientDTO> listClients() {
        return clientRepository.findAll().stream()
                .map(mapper::fromClient).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClient(Long id) throws ClientNotFoundException {
        return clientRepository.findById(id)
                .map(mapper::fromClient)
                .orElseThrow(() -> new ClientNotFoundException("Client " + id + " introuvable"));
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = mapper.toClient(clientDTO);
        return mapper.fromClient(clientRepository.save(client));
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) throws ClientNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client " + id + " introuvable"));
        client.setNom(clientDTO.getNom());
        client.setEmail(clientDTO.getEmail());
        return mapper.fromClient(clientRepository.save(client));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    // Contrats
    @Override
    public ContratAutomobileDTO createContratAutomobile(CreateContratRequestDTO dto) throws ClientNotFoundException {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client introuvable"));
        ContratAutomobile ca = new ContratAutomobile();
        ca.setId(UUID.randomUUID().toString());
        ca.setClient(client);
        ca.setDateSouscription(dto.getDateSouscription() != null ? dto.getDateSouscription() : new Date());
        ca.setStatut(StatutContrat.EN_COURS);
        ca.setMontantCotisation(dto.getMontantCotisation());
        ca.setDureeContrat(dto.getDureeContrat());
        ca.setTauxCouverture(dto.getTauxCouverture());
        ca.setImmatriculation(dto.getImmatriculation());
        ca.setMarque(dto.getMarque());
        ca.setModele(dto.getModele());
        return mapper.fromContratAutomobile(contratRepository.save(ca));
    }

    @Override
    public ContratHabitationDTO createContratHabitation(CreateContratRequestDTO dto) throws ClientNotFoundException {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client introuvable"));
        ContratHabitation ch = new ContratHabitation();
        ch.setId(UUID.randomUUID().toString());
        ch.setClient(client);
        ch.setDateSouscription(dto.getDateSouscription() != null ? dto.getDateSouscription() : new Date());
        ch.setStatut(StatutContrat.EN_COURS);
        ch.setMontantCotisation(dto.getMontantCotisation());
        ch.setDureeContrat(dto.getDureeContrat());
        ch.setTauxCouverture(dto.getTauxCouverture());
        ch.setTypeLogement(dto.getTypeLogement());
        ch.setAdresse(dto.getAdresse());
        ch.setSuperficie(dto.getSuperficie());
        return mapper.fromContratHabitation(contratRepository.save(ch));
    }

    @Override
    public ContratSanteDTO createContratSante(CreateContratRequestDTO dto) throws ClientNotFoundException {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client introuvable"));
        ContratSante cs = new ContratSante();
        cs.setId(UUID.randomUUID().toString());
        cs.setClient(client);
        cs.setDateSouscription(dto.getDateSouscription() != null ? dto.getDateSouscription() : new Date());
        cs.setStatut(StatutContrat.EN_COURS);
        cs.setMontantCotisation(dto.getMontantCotisation());
        cs.setDureeContrat(dto.getDureeContrat());
        cs.setTauxCouverture(dto.getTauxCouverture());
        cs.setNiveauCouverture(dto.getNiveauCouverture());
        cs.setNbPersonnesCouvertes(dto.getNbPersonnesCouvertes());
        return mapper.fromContratSante(contratRepository.save(cs));
    }

    @Override
    public ContratDTO getContrat(String id) throws ContratNotFoundException {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ContratNotFoundException("Contrat introuvable"));
        if (contrat instanceof ContratAutomobile) return mapper.fromContratAutomobile((ContratAutomobile) contrat);
        if (contrat instanceof ContratHabitation) return mapper.fromContratHabitation((ContratHabitation) contrat);
        return mapper.fromContratSante((ContratSante) contrat);
    }

    @Override
    public List<ContratDTO> listContrats() {
        return contratRepository.findAll().stream().map(c -> {
            if (c instanceof ContratAutomobile) return (ContratDTO) mapper.fromContratAutomobile((ContratAutomobile) c);
            if (c instanceof ContratHabitation) return mapper.fromContratHabitation((ContratHabitation) c);
            return mapper.fromContratSante((ContratSante) c);
        }).collect(Collectors.toList());
    }

    @Override
    public void updateStatut(String id, String nouveauStatut) throws ContratNotFoundException {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new ContratNotFoundException("Contrat introuvable"));
        contrat.setStatut(StatutContrat.valueOf(nouveauStatut));
        if (nouveauStatut.equals("VALIDE")) contrat.setDateValidation(new Date());
        contratRepository.save(contrat);
    }

    @Override
    public PaiementDTO addPaiement(PaiementRequestDTO dto) throws ContratNotFoundException {
        Contrat contrat = contratRepository.findById(dto.getContratId())
                .orElseThrow(() -> new ContratNotFoundException("Contrat introuvable"));
        Paiement paiement = new Paiement();
        paiement.setDate(new Date());
        paiement.setMontant(dto.getMontant());
        paiement.setTypePaiement(dto.getTypePaiement());
        paiement.setContrat(contrat);
        return mapper.fromPaiement(paiementRepository.save(paiement));
    }

    @Override
    public List<PaiementDTO> getPaiementsByContrat(String contratId, int page, int size) throws ContratNotFoundException {
        if (!contratRepository.existsById(contratId))
            throw new ContratNotFoundException("Contrat introuvable");
        return paiementRepository.findByContratIdOrderByDateDesc(contratId, PageRequest.of(page, size))
                .stream().map(mapper::fromPaiement).collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> searchClients(String nom) {
        return clientRepository.findByNomContainsIgnoreCase(nom).stream()
                .map(mapper::fromClient).collect(Collectors.toList());
    }

    @Override
    public List<ContratDTO> searchContrats(String keyword, String type, String statut) {
        return contratRepository.findAll().stream()
                .filter(c -> {
                    boolean b = true;
                    if (keyword != null && !keyword.isEmpty())
                        b = (c.getClient().getNom().toLowerCase().contains(keyword.toLowerCase()));
                    if (type != null && !type.isEmpty())
                        b = b && c.getClass().getSimpleName().equalsIgnoreCase(type);
                    if (statut != null && !statut.isEmpty())
                        b = b && c.getStatut().name().equalsIgnoreCase(statut);
                    return b;
                })
                .map(c -> {
                    if (c instanceof ContratAutomobile) return (ContratDTO) mapper.fromContratAutomobile((ContratAutomobile) c);
                    if (c instanceof ContratHabitation) return mapper.fromContratHabitation((ContratHabitation) c);
                    return mapper.fromContratSante((ContratSante) c);
                }).collect(Collectors.toList());
    }

    @Override
    public long getTotalClients() {
        return clientRepository.count();
    }

    @Override
    public long getTotalContrats() {
        return contratRepository.count();
    }

    @Override
    public double getTotalCotisations() {
        return contratRepository.findAll().stream()
                .mapToDouble(Contrat::getMontantCotisation).sum();
    }

    @Override
    public long countContratsByStatut(String statut) {
        return contratRepository.findAll().stream()
                .filter(c -> c.getStatut().name().equals(statut)).count();
    }
}