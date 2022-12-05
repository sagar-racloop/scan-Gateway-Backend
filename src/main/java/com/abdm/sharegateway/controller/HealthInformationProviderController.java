package com.abdm.sharegateway.controller;

import com.abdm.sharegateway.co.HealthInformationProviderCO;
import com.abdm.sharegateway.domain.HealthInformationProvider;
import com.abdm.sharegateway.service.HealthInformationProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/hip")
public class HealthInformationProviderController {

    @Autowired
    private HealthInformationProviderService healthInformationProviderService;

    @PostMapping()
    public ResponseEntity create(@Valid @RequestBody HealthInformationProviderCO healthInformationProviderCO) {
        Long id = healthInformationProviderService.save(healthInformationProviderCO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping()
    public ResponseEntity<List<HealthInformationProvider>> list() {
        return ResponseEntity.ok(healthInformationProviderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthInformationProvider> get(@PathVariable Long id) {
        return ResponseEntity.ok(healthInformationProviderService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HealthInformationProvider> delete(@PathVariable Long id) {
        return ResponseEntity.ok(healthInformationProviderService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthInformationProvider> update(
            @PathVariable Long id, @Valid @RequestBody HealthInformationProviderCO healthInformationProviderCO) {
        return ResponseEntity.ok(healthInformationProviderService.update(id, healthInformationProviderCO));
    }
}
