package com.abdm.sharegateway.controller;

import com.abdm.sharegateway.domain.HealthInformationProvider;
import com.abdm.sharegateway.service.HealthInformationProviderService;
import com.abdm.sharegateway.vo.HealthInformationProviderDetailedVO;
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

    @CrossOrigin
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
    @CrossOrigin
    public ResponseEntity<List<HealthInformationProviderVO>> list() {
        return ResponseEntity.ok(healthInformationProviderService.getAll());
    }

    @CrossOrigin
    @GetMapping("/{hipId}/counter/{counterId}")
    public ResponseEntity<HealthInformationProviderDetailedVO> get(@PathVariable String hipId, @PathVariable String counterId) {
        return ResponseEntity.ok(healthInformationProviderService.get(hipId, counterId));
    }

    @CrossOrigin
    @DeleteMapping("/{hipId}/counter/{counterId}")
    public ResponseEntity<HealthInformationProvider> delete(@PathVariable String hipId, @PathVariable String counterId) {
        return ResponseEntity.ok(healthInformationProviderService.delete(hipId, counterId));
    }

    @CrossOrigin
    @PutMapping("/{hipId}/counter/{counterId}")
    public ResponseEntity<HealthInformationProvider> update(@PathVariable String hipId, @PathVariable String counterId,
                                                            @Valid @RequestPart String healthInformationProviderCO,
                                                            @Valid @RequestPart MultipartFile multipartFile) {
        return ResponseEntity.ok(healthInformationProviderService.update(hipId, counterId, healthInformationProviderCO,
                multipartFile));
    }
}
