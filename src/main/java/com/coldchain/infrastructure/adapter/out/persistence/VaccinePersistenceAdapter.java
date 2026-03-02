package com.coldchain.infrastructure.adapter.out.persistence;

import com.coldchain.application.port.out.IVaccineRepository;
import com.coldchain.domain.model.VaccineBatch;
import com.coldchain.infrastructure.adapter.out.persistence.entity.VaccineBatchEntity;
import com.coldchain.infrastructure.adapter.out.persistence.entity.StorageUnitEntity;
import com.coldchain.infrastructure.adapter.out.persistence.repository.JpaVaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VaccinePersistenceAdapter implements IVaccineRepository {

    private final JpaVaccineRepository jpaVaccineRepository;

    @Override
    public VaccineBatch save(VaccineBatch batch) {
        VaccineBatchEntity entity = mapToEntity(batch);
        VaccineBatchEntity savedEntity = jpaVaccineRepository.save(entity);
        return mapToDomain(savedEntity); // Check this line
    }

    @Override
    public Optional<VaccineBatch> findByBatchNumber(String batchNumber) {
        return jpaVaccineRepository.findById(batchNumber).map(this::mapToDomain); // Check this line
    }

    private VaccineBatchEntity mapToEntity(VaccineBatch domain) {
        VaccineBatchEntity entity = new VaccineBatchEntity();
        entity.setBatchNumber(domain.getBatchNumber());
        entity.setVaccineType(domain.getVaccineType());
        entity.setExpiryDate(domain.getExpiryDate());

        if (domain.getStorageUnit() != null) {
            StorageUnitEntity storageEntity = new StorageUnitEntity();
            storageEntity.setUnitId(domain.getStorageUnit().getUnitId());
            entity.setStorageUnit(storageEntity);
        }

        return entity;
    }

    // Ensure this method exists and takes VaccineBatchEntity as parameter
    private VaccineBatch mapToDomain(VaccineBatchEntity entity) {
        // Here you would map StorageUnitEntity back to StorageUnit domain model if needed
        return new VaccineBatch(
                entity.getBatchNumber(),
                entity.getVaccineType(),
                entity.getExpiryDate(),
                null // Mapping storage unit back needs care
        );
    }
}