package org.enset.touahrighizlaneexam.repositories;

import org.enset.touahrighizlaneexam.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
