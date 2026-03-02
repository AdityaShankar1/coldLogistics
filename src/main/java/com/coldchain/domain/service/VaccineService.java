package com.coldchain.domain.service;

import com.coldchain.application.port.in.IVaccineService;
import com.coldchain.application.port.out.IStorageRepository;
import com.coldchain.application.port.out.IVaccineRepository;
import com.coldchain.domain.model.StorageUnit;
import com.coldchain.domain.model.VaccineBatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // This generates the constructor with required fields
public class VaccineService implements IVaccineService {

    private final IVaccineRepository vaccineRepository;
    private final IStorageRepository storageRepository;

    @Override
    public VaccineBatch registerBatch(String batchNumber, String vaccineType, LocalDate expiryDate) {
        VaccineBatch batch = new VaccineBatch(batchNumber, vaccineType, expiryDate, null);
        return vaccineRepository.save(batch);
    }

    @Override
    public VaccineBatch assignStorage(String batchNumber, String storageUnitId) {
        VaccineBatch batch = vaccineRepository.findByBatchNumber(batchNumber)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        StorageUnit unit = storageRepository.findById(storageUnitId)
                .orElseThrow(() -> new RuntimeException("Storage unit not found"));

        batch.assignToStorage(unit);
        return vaccineRepository.save(batch);
    }

    @Override
    public List<VaccineBatch> getAllBatches() {
        return vaccineRepository.findAll();
    }

    @Override
    public Optional<VaccineBatch> getBatch(String batchNumber) {
        return vaccineRepository.findByBatchNumber(batchNumber);
    }
}