package org.enset.touahrighizlaneexam.repositories;

import org.enset.touahrighizlaneexam.entities.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContratRepository extends JpaRepository<Contrat, String>, JpaSpecificationExecutor<Contrat> {
}
