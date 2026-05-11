package org.enset.touahrighizlaneexam.repositories;

import org.enset.touahrighizlaneexam.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNomContainsIgnoreCase(String nom);
}