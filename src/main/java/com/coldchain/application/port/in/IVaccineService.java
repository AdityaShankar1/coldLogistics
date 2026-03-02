package com.coldchain.application.port.in;

import com.coldchain.domain.model.VaccineBatch;

public interface IVaccineService {
    VaccineBatch registerBatch(String batchNumber, String vaccineType, String expiryDate);
    VaccineBatch assignStorage(String batchNumber, String storageUnitId);
}
