package com.coldchain.application.port.in;

import com.coldchain.domain.model.VaccineBatch;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IVaccineService {
    // MUST MATCH VaccineService
    VaccineBatch registerBatch(String batchNumber, String vaccineType, LocalDate expiryDate);

    VaccineBatch assignStorage(String batchNumber, String storageUnitId);

    List<VaccineBatch> getAllBatches();

    Optional<VaccineBatch> getBatch(String batchNumber);
}