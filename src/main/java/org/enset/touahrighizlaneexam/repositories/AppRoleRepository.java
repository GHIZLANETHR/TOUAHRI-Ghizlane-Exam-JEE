package org.enset.touahrighizlaneexam.repositories;

import org.enset.touahrighizlaneexam.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);
}
