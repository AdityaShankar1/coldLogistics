package com.coldchain.infrastructure.adapter.in.web;

import com.coldchain.application.port.in.IVaccineService;
import com.coldchain.domain.model.VaccineBatch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate; // <-- ADD THIS LINE
import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final IVaccineService vaccineService; // Inject the service

    @GetMapping
    public ResponseEntity<List<VaccineBatch>> getAllBatches() {
        // Use the service method
        return ResponseEntity.ok(vaccineService.getAllBatches());
    }

    @GetMapping("/{batchNumber}")
    public ResponseEntity<VaccineBatch> getBatchById(@PathVariable String batchNumber) {
        return vaccineService.getBatch(batchNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VaccineBatch> registerBatch(@RequestBody RegisterBatchRequest request) {
        VaccineBatch batch = vaccineService.registerBatch(
                request.batchNumber(),
                request.vaccineType(),
                request.expiryDate());
        return ResponseEntity.ok(batch);
    }

    public record RegisterBatchRequest(String batchNumber, String vaccineType, LocalDate expiryDate) {
    }
}