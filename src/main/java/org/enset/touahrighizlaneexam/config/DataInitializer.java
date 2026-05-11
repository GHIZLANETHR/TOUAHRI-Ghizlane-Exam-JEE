package org.enset.touahrighizlaneexam.config;

import org.enset.touahrighizlaneexam.entities.*;
import org.enset.touahrighizlaneexam.enums.*;
import org.enset.touahrighizlaneexam.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(ClientRepository clientRepo,
                               ContratRepository contratRepo,
                               PaiementRepository paiementRepo) {
        return args -> {
            // Création de 3 clients
            Client c1 = clientRepo.save(Client.builder().nom("Ahmed").email("ahmed@mail.com").build());
            Client c2 = clientRepo.save(Client.builder().nom("Fatima").email("fatima@mail.com").build());
            Client c3 = clientRepo.save(Client.builder().nom("Youssef").email("youssef@mail.com").build());

            // Contrat Automobile pour Ahmed
            ContratAutomobile ca = new ContratAutomobile();
            ca.setId(UUID.randomUUID().toString());
            ca.setDateSouscription(new Date());
            ca.setStatut(StatutContrat.EN_COURS);
            ca.setMontantCotisation(3200);
            ca.setDureeContrat(12);
            ca.setTauxCouverture(0.75);
            ca.setClient(c1);
            ca.setImmatriculation("123-A-45");
            ca.setMarque("Renault");
            ca.setModele("Clio");
            contratRepo.save(ca);

            // Contrat Habitation pour Ahmed
            ContratHabitation ch = new ContratHabitation();
            ch.setId(UUID.randomUUID().toString());
            ch.setDateSouscription(new Date());
            ch.setStatut(StatutContrat.VALIDE);
            ch.setDateValidation(new Date());
            ch.setMontantCotisation(2800);
            ch.setDureeContrat(24);
            ch.setTauxCouverture(0.85);
            ch.setClient(c1);
            ch.setTypeLogement(TypeLogement.APPARTEMENT);
            ch.setAdresse("10 rue Ibn Sina, Casablanca");
            ch.setSuperficie(90);
            contratRepo.save(ch);

            // Contrat Santé pour Fatima
            ContratSante cs = new ContratSante();
            cs.setId(UUID.randomUUID().toString());
            cs.setDateSouscription(new Date());
            cs.setStatut(StatutContrat.EN_COURS);
            cs.setMontantCotisation(4500);
            cs.setDureeContrat(12);
            cs.setTauxCouverture(0.9);
            cs.setClient(c2);
            cs.setNiveauCouverture(NiveauCouverture.INTERMEDIAIRE);
            cs.setNbPersonnesCouvertes(4);
            contratRepo.save(cs);

            // Contrat Automobile pour Fatima
            ContratAutomobile ca2 = new ContratAutomobile();
            ca2.setId(UUID.randomUUID().toString());
            ca2.setDateSouscription(new Date());
            ca2.setStatut(StatutContrat.RESILIE);
            ca2.setDateValidation(new Date());
            ca2.setMontantCotisation(4000);
            ca2.setDureeContrat(12);
            ca2.setTauxCouverture(0.8);
            ca2.setClient(c2);
            ca2.setImmatriculation("25-B-67");
            ca2.setMarque("Dacia");
            ca2.setModele("Sandero");
            contratRepo.save(ca2);

            // Contrat Habitation pour Youssef
            ContratHabitation ch2 = new ContratHabitation();
            ch2.setId(UUID.randomUUID().toString());
            ch2.setDateSouscription(new Date());
            ch2.setStatut(StatutContrat.EN_COURS);
            ch2.setMontantCotisation(3800);
            ch2.setDureeContrat(36);
            ch2.setTauxCouverture(0.8);
            ch2.setClient(c3);
            ch2.setTypeLogement(TypeLogement.MAISON);
            ch2.setAdresse("5 avenue Mohammed V, Rabat");
            ch2.setSuperficie(120);
            contratRepo.save(ch2);

            // Paiements pour les contrats
            paiementRepo.save(Paiement.builder()
                    .date(new Date()).montant(800).typePaiement(TypePaiement.MENSUALITE).contrat(ca).build());
            paiementRepo.save(Paiement.builder()
                    .date(new Date()).montant(800).typePaiement(TypePaiement.MENSUALITE).contrat(ca).build());
            paiementRepo.save(Paiement.builder()
                    .date(new Date()).montant(3000).typePaiement(TypePaiement.PAIEMENT_ANNUEL).contrat(ch).build());
            paiementRepo.save(Paiement.builder()
                    .date(new Date()).montant(1500).typePaiement(TypePaiement.MENSUALITE).contrat(cs).build());
            paiementRepo.save(Paiement.builder()
                    .date(new Date()).montant(2000).typePaiement(TypePaiement.PAIEMENT_EXCEPTIONNEL).contrat(ca2).build());
        };
    }

    @Bean
    CommandLineRunner initUsers(AppUserRepository userRepo, AppRoleRepository roleRepo, PasswordEncoder pe) {
        return args -> {
            AppRole clientRole = roleRepo.save(AppRole.builder().roleName("ROLE_CLIENT").build());
            AppRole employeRole = roleRepo.save(AppRole.builder().roleName("ROLE_EMPLOYE").build());
            AppRole adminRole = roleRepo.save(AppRole.builder().roleName("ROLE_ADMIN").build());

            userRepo.save(AppUser.builder().username("client").password(pe.encode("1234"))
                    .email("client@mail.com").roles(List.of(clientRole)).build());
            userRepo.save(AppUser.builder().username("employe").password(pe.encode("1234"))
                    .email("employe@mail.com").roles(List.of(employeRole)).build());
            userRepo.save(AppUser.builder().username("admin").password(pe.encode("1234"))
                    .email("admin@mail.com").roles(List.of(clientRole, employeRole, adminRole)).build());
        };
    }
}