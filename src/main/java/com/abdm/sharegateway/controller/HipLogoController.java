package com.abdm.sharegateway.controller;

import com.abdm.sharegateway.domain.HipLogo;
import com.abdm.sharegateway.service.HipLogoService;
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
@RequestMapping("/api/hip-logo")
public class HipLogoController {

    @Autowired
    private HipLogoService hipLogoService;

    @GetMapping("/{id}")
    public ResponseEntity<HipLogo> fetch(@PathVariable Long id) {
        return ResponseEntity.ok(hipLogoService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<HipLogo>> fetchAll() {
        return ResponseEntity.ok(hipLogoService.getAll());
    }

    @PostMapping(path = "/upload", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> save(@Valid @RequestPart String hipLogoCo, @Valid @RequestPart MultipartFile imageFile) {
        Long id = hipLogoService.
                save(hipLogoCo, imageFile);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        hipLogoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hipId/{hipId}")
    public ResponseEntity<HipLogo> findByHipId(@PathVariable String hipId) {
        return ResponseEntity.ok(hipLogoService.findByHipId(hipId));
    }

    @PutMapping("/hipId/{hipId}")
    public ResponseEntity<Object> update(@PathVariable String hipId,
                                         @Valid @RequestPart MultipartFile imageFile) {
        hipLogoService.updateLogoForHip(hipId, imageFile);
        return ResponseEntity.ok().build();
    }
}
