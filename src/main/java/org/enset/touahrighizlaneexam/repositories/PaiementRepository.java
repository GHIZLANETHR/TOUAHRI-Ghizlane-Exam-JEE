package org.enset.touahrighizlaneexam.repositories;


import org.enset.touahrighizlaneexam.entities.Paiement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    Page<Paiement> findByContratIdOrderByDateDesc(String contratId, Pageable pageable);
}
