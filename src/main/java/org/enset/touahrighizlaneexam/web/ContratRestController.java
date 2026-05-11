package org.enset.touahrighizlaneexam.web;

import lombok.AllArgsConstructor;
import org.enset.touahrighizlaneexam.dtos.*;
import org.enset.touahrighizlaneexam.exceptions.*;
import org.enset.touahrighizlaneexam.services.AssuranceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@AllArgsConstructor
public class ContratRestController {
    private AssuranceService service;

    @PostMapping("/automobile")
    @PreAuthorize("hasAnyAuthority('SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public ContratAutomobileDTO createAutomobile(@RequestBody CreateContratRequestDTO dto) throws ClientNotFoundException {
        return service.createContratAutomobile(dto);
    }

    @PostMapping("/habitation")
    @PreAuthorize("hasAnyAuthority('SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public ContratHabitationDTO createHabitation(@RequestBody CreateContratRequestDTO dto) throws ClientNotFoundException {
        return service.createContratHabitation(dto);
    }

    @PostMapping("/sante")
    @PreAuthorize("hasAnyAuthority('SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public ContratSanteDTO createSante(@RequestBody CreateContratRequestDTO dto) throws ClientNotFoundException {
        return service.createContratSante(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_CLIENT','SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public ContratDTO getById(@PathVariable String id) throws ContratNotFoundException {
        return service.getContrat(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public List<ContratDTO> getAll() {
        return service.listContrats();
    }

    @PatchMapping("/{id}/statut")
    @PreAuthorize("hasAnyAuthority('SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public void changeStatut(@PathVariable String id, @RequestParam String statut) throws ContratNotFoundException {
        service.updateStatut(id, statut);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public List<ContratDTO> search(@RequestParam(required = false) String keyword,
                                   @RequestParam(required = false) String type,
                                   @RequestParam(required = false) String statut) {
        return service.searchContrats(keyword, type, statut);
    }
}
