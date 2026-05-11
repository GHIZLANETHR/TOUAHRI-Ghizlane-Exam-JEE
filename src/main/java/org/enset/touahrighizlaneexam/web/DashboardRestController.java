package org.enset.touahrighizlaneexam.web;

import lombok.AllArgsConstructor;
import org.enset.touahrighizlaneexam.services.AssuranceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@AllArgsConstructor
public class DashboardRestController {
    private AssuranceService service;

    @GetMapping("/stats")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Map<String, Object> stats() {
        return Map.of(
                "totalClients", service.getTotalClients(),
                "totalContrats", service.getTotalContrats(),
                "totalCotisations", service.getTotalCotisations(),
                "contratsEnCours", service.countContratsByStatut("EN_COURS"),
                "contratsValides", service.countContratsByStatut("VALIDE"),
                "contratsResilies", service.countContratsByStatut("RESILIE")
        );
    }
}
