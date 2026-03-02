package com.coldchain.infrastructure.adapter.in.web;

import com.coldchain.application.port.in.IVaccineService;
import com.coldchain.domain.model.VaccineBatch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final IVaccineService vaccineService;

    @PostMapping
    public ResponseEntity<VaccineBatch> registerBatch(@RequestBody RegisterBatchRequest request) {
        VaccineBatch batch = vaccineService.registerBatch(
            request.batchNumber(), 
            request.vaccineType(), 
            request.expiryDate()
        );
        return ResponseEntity.ok(batch);
    }

    // Record for request body
    public record RegisterBatchRequest(String batchNumber, String vaccineType, String expiryDate) {}
}
