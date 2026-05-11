package org.enset.touahrighizlaneexam.web;

import lombok.AllArgsConstructor;
import org.enset.touahrighizlaneexam.dtos.PaiementDTO;
import org.enset.touahrighizlaneexam.dtos.PaiementRequestDTO;
import org.enset.touahrighizlaneexam.exceptions.ContratNotFoundException;
import org.enset.touahrighizlaneexam.services.AssuranceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@AllArgsConstructor
public class PaiementRestController {
    private AssuranceService service;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public PaiementDTO add(@RequestBody PaiementRequestDTO dto) throws ContratNotFoundException {
        return service.addPaiement(dto);
    }

    @GetMapping("/contrat/{contratId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_CLIENT','SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public List<PaiementDTO> byContrat(@PathVariable String contratId,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size) throws ContratNotFoundException {
        return service.getPaiementsByContrat(contratId, page, size);
    }
}
