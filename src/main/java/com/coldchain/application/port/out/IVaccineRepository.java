package com.coldchain.application.port.out;

import com.coldchain.domain.model.VaccineBatch;
import java.util.Optional;

public interface IVaccineRepository {
    VaccineBatch save(VaccineBatch batch);
    Optional<VaccineBatch> findByBatchNumber(String batchNumber);
}
