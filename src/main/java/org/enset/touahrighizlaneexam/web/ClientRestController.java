package org.enset.touahrighizlaneexam.web;

import lombok.AllArgsConstructor;
import org.enset.touahrighizlaneexam.dtos.ClientDTO;
import org.enset.touahrighizlaneexam.exceptions.ClientNotFoundException;
import org.enset.touahrighizlaneexam.services.AssuranceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
public class ClientRestController {
    private AssuranceService service;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_CLIENT','SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public List<ClientDTO> getAll() {
        return service.listClients();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_CLIENT','SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public ClientDTO getById(@PathVariable Long id) throws ClientNotFoundException {
        return service.getClient(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public ClientDTO create(@RequestBody ClientDTO dto) {
        return service.saveClient(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public ClientDTO update(@PathVariable Long id, @RequestBody ClientDTO dto) throws ClientNotFoundException {
        return service.updateClient(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void delete(@PathVariable Long id) {
        service.deleteClient(id);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('SCOPE_CLIENT','SCOPE_EMPLOYE','SCOPE_ADMIN')")
    public List<ClientDTO> search(@RequestParam String nom) {
        return service.searchClients(nom);
    }
}