package com.abdm.sharegateway.controller;

import com.abdm.sharegateway.co.HealthInformationProviderCO;
import com.abdm.sharegateway.domain.HealthInformationProvider;
import com.abdm.sharegateway.service.HealthInformationProviderService;
import com.abdm.sharegateway.vo.HealthInformationProviderVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/hip")
public class HealthInformationProviderController {

    @Autowired
    private HealthInformationProviderService healthInformationProviderService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity create(@Valid @RequestPart String healthInformationProviderCO,
                                 @Valid @RequestPart MultipartFile multipartFile) {
        Long id = healthInformationProviderService.save(healthInformationProviderCO, multipartFile);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping()
    public ResponseEntity<List<HealthInformationProviderVO>> list() {
        return ResponseEntity.ok(healthInformationProviderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthInformationProvider> get(@PathVariable Long id) {
        return ResponseEntity.ok(healthInformationProviderService.get(id));
    }

    @GetMapping("/{hipId}/counter/{counterId}")
    public ResponseEntity<HealthInformationProviderVO> get(@PathVariable String hipId, @PathVariable String counterId) {
        return ResponseEntity.ok(healthInformationProviderService.getForHipAndCounter(hipId, counterId));
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
